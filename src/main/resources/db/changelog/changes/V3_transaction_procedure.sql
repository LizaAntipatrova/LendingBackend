CREATE OR REPLACE PROCEDURE create_monthly_payment(
    p_contract_number BIGINT,
    p_payment_amount DECIMAL(15,2))
    LANGUAGE plpgsql
AS $$
DECLARE
    v_credit_status VARCHAR(20);
    v_current_amount DECIMAL(15,2);
BEGIN
    -- Получаем текущий статус и сумму кредита
    SELECT status, current_amount INTO v_credit_status, v_current_amount
    FROM credits
    WHERE contract_number = p_contract_number;

    -- Проверяем, что кредит активен или просрочен
    IF v_credit_status IN ('ACTIVE', 'EXPIRED') THEN
        -- Вставляем запись о платеже
        INSERT INTO transactions (transaction_date, credit_id, amount, description)
        VALUES (CURRENT_DATE, p_contract_number, p_payment_amount, 'MONTHLY_PAYMENT');

        -- Обновляем текущую сумму долга
        UPDATE credits
        SET current_amount = v_current_amount - p_payment_amount
        WHERE contract_number = p_contract_number
        RETURNING current_amount INTO v_current_amount;

        -- Проверяем, полностью ли погашен кредит
        IF v_current_amount <= 0 THEN
            UPDATE credits
            SET status = 'CLOSED',
                current_amount = 0
            WHERE contract_number = p_contract_number;
        END IF;
    END IF;
END;
$$;

-- 2. Процедура для начисления штрафа (20% годовых, ежедневное начисление)
CREATE OR REPLACE PROCEDURE charge_penalty(
    p_contract_number BIGINT)
LANGUAGE plpgsql
AS $$
DECLARE
    v_current_amount DECIMAL(15,2);
    v_daily_penalty DECIMAL(15,2);
BEGIN
    -- Получаем текущую сумму долга
    SELECT current_amount INTO v_current_amount
    FROM credits
    WHERE contract_number = p_contract_number;

    -- Рассчитываем дневной штраф (20% годовых / 365 дней)
    v_daily_penalty := v_current_amount * 0.20 / 365;

    -- Вставляем запись о штрафе
    INSERT INTO transactions (transaction_date, credit_id, amount, description)
    VALUES (CURRENT_DATE, p_contract_number, v_daily_penalty, 'PENALTIES');

    -- Увеличиваем текущую сумму долга на размер штрафа
    UPDATE credits
    SET current_amount = current_amount + v_daily_penalty
    WHERE contract_number = p_contract_number;

END;
$$;

-- 3. Процедура для начисления процентов (ежемесячное начисление)
CREATE OR REPLACE PROCEDURE charge_interest(
    p_contract_number BIGINT)
LANGUAGE plpgsql
AS $$
DECLARE
    v_current_amount DECIMAL(15,2);
    v_interest_rate DECIMAL(5,2);
    v_monthly_interest DECIMAL(15,2);
    v_product_id BIGINT;
BEGIN
    -- Получаем текущую сумму долга и процентную ставку
    SELECT c.current_amount, cp.interest_rate INTO v_current_amount, v_interest_rate
    FROM credits c
    JOIN applications ca ON c.application_id = ca.id
    JOIN credit_products cp ON ca.product_id = cp.code
    WHERE c.contract_number = p_contract_number;

    -- Рассчитываем ежемесячные проценты
    v_monthly_interest := v_current_amount * (v_interest_rate / 100) / 12;

    -- Вставляем запись о начислении процентов
    INSERT INTO transactions (transaction_date, credit_id, amount, description)
    VALUES (CURRENT_DATE, p_contract_number, v_monthly_interest, 'COMMISSION');

    -- Увеличиваем текущую сумму долга на размер процентов
    UPDATE credits
    SET current_amount = current_amount + v_monthly_interest
    WHERE contract_number = p_contract_number;

END;
$$;

-- 4. Процедура для досрочного погашения
CREATE OR REPLACE PROCEDURE early_repayment(
    p_contract_number BIGINT,
    p_repayment_amount DECIMAL(15,2))
    LANGUAGE plpgsql
AS $$
DECLARE
    v_credit_status VARCHAR(20);
    v_current_amount DECIMAL(15,2);
BEGIN
    -- Получаем текущий статус и сумму кредита
    SELECT status, current_amount INTO v_credit_status, v_current_amount
    FROM credits
    WHERE contract_number = p_contract_number;

    -- Проверяем, что кредит активен или просрочен
    IF v_credit_status IN ('ACTIVE', 'EXPIRED') THEN
        -- Вставляем запись о досрочном погашении
        INSERT INTO transactions (transaction_date, credit_id, amount, description)
        VALUES (CURRENT_DATE, p_contract_number, p_repayment_amount, 'EARLY_REPAYMENT');

        -- Обновляем текущую сумму долга
        UPDATE credits
        SET current_amount = v_current_amount - p_repayment_amount
        WHERE contract_number = p_contract_number
        RETURNING current_amount INTO v_current_amount;

        -- Проверяем, полностью ли погашен кредит
        IF v_current_amount <= 0 THEN
            UPDATE credits
            SET status = 'CLOSED',
                current_amount = 0
            WHERE contract_number = p_contract_number;
        END IF;
    END IF;
END;
$$;