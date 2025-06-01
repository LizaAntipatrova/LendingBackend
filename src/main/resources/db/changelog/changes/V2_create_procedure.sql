CREATE OR REPLACE PROCEDURE add_credit_category(
    p_text_name VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO credit_categories (text_name)
    VALUES (p_text_name);

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE add_credit_product(
    p_name VARCHAR,
    p_interest_rate NUMERIC,
    p_min_amount NUMERIC,
    p_max_amount NUMERIC,
    p_min_term INTEGER,
    p_max_term INTEGER,
    p_min_down_payment NUMERIC,
    p_description TEXT,
    p_category_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO credit_products (
        name,
        interest_rate,
        min_amount,
        max_amount,
        min_term,
        max_term,
        min_down_payment,
        description,
        category_id
    )
    VALUES (
               p_name,
               p_interest_rate,
               p_min_amount,
               p_max_amount,
               p_min_term,
               p_max_term,
               p_min_down_payment,
               p_description,
               p_category_id
           );

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE update_credit_product(
    p_code BIGINT,
    p_name VARCHAR,
    p_interest_rate NUMERIC,
    p_min_amount NUMERIC,
    p_max_amount NUMERIC,
    p_min_term INTEGER,
    p_max_term INTEGER,
    p_min_down_payment NUMERIC,
    p_description TEXT,
    p_category_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE credit_products
    SET
        name = p_name,
        interest_rate = p_interest_rate,
        min_amount = p_min_amount,
        max_amount = p_max_amount,
        min_term = p_min_term,
        max_term = p_max_term,
        min_down_payment = p_min_down_payment,
        description = p_description,
        category_id = p_category_id
    WHERE code = p_code;

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_credit_product(
    p_code BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM credit_products
    WHERE code = p_code;

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE add_manager(
    p_last_name VARCHAR,
    p_first_name VARCHAR,
    p_middle_name VARCHAR,
    p_phone VARCHAR,
    p_email VARCHAR,
    p_login VARCHAR,
    p_password VARCHAR,
    p_specialization_ids BIGINT[],
    OUT p_employee_id BIGINT
)
    LANGUAGE plpgsql
AS $$
DECLARE
    v_user_id BIGINT;
    v_spec_id BIGINT;
BEGIN
    -- Создаем пользователя с ролью MANAGER
    INSERT INTO users (login, password, registration_date, role)
    VALUES (p_login, p_password, NOW(), 'MANAGER')
    RETURNING id INTO v_user_id;

    -- Создаем менеджера
    INSERT INTO managers (last_name, first_name, middle_name, phone, email, user_id)
    VALUES (p_last_name, p_first_name, p_middle_name, p_phone, p_email, v_user_id)
    RETURNING employee_id INTO p_employee_id;

    -- Добавляем специализации
    FOREACH v_spec_id IN ARRAY p_specialization_ids LOOP
            INSERT INTO manager_specializations (manager_id, specialization_id)
            VALUES (p_employee_id, v_spec_id);
        END LOOP;

    COMMIT;
END;
$$;
CREATE OR REPLACE PROCEDURE update_manager(
    p_employee_id BIGINT,
    p_last_name VARCHAR,
    p_first_name VARCHAR,
    p_middle_name VARCHAR,
    p_phone VARCHAR,
    p_email VARCHAR,
    p_specialization_ids BIGINT[]
)
    LANGUAGE plpgsql
AS $$
DECLARE
    v_spec_id BIGINT;
BEGIN
    -- Обновляем данные менеджера
    UPDATE managers
    SET
        last_name = p_last_name,
        first_name = p_first_name,
        middle_name = p_middle_name,
        phone = p_phone,
        email = p_email
    WHERE employee_id = p_employee_id;

    -- Удаляем старые специализации
    DELETE FROM manager_specializations WHERE manager_id = p_employee_id;

    -- Добавляем новые специализации
    FOREACH v_spec_id IN ARRAY p_specialization_ids LOOP
            INSERT INTO manager_specializations (manager_id, specialization_id)
            VALUES (p_employee_id, v_spec_id);
        END LOOP;

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_manager(
    p_employee_id BIGINT
)
    LANGUAGE plpgsql
AS $$
DECLARE
    v_user_id BIGINT;
BEGIN
    -- Получаем ID пользователя
    SELECT user_id INTO v_user_id FROM managers WHERE employee_id = p_employee_id;

    -- Удаляем менеджера
    DELETE FROM managers WHERE employee_id = p_employee_id;

    -- Удаляем пользователя
    DELETE FROM users WHERE id = v_user_id;

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE add_client(
    p_last_name VARCHAR,
    p_first_name VARCHAR,
    p_middle_name VARCHAR,
    p_passport_series VARCHAR,
    p_passport_number VARCHAR,
    p_birth_date DATE,
    p_address TEXT,
    p_phone VARCHAR,
    p_email VARCHAR,
    p_login VARCHAR,
    p_password VARCHAR,
    OUT p_account_number VARCHAR
)
    LANGUAGE plpgsql
AS $$
DECLARE
    v_user_id BIGINT;
    v_account_number VARCHAR;
BEGIN

    -- Создаем пользователя с ролью CLIENT
    INSERT INTO users (login, password, registration_date, role)
    VALUES (p_login, p_password, NOW(), 'CLIENT')
    RETURNING id INTO v_user_id;


    -- Создаем клиента
    INSERT INTO clients (
        last_name,
        first_name,
        middle_name,
        passport_series,
        passport_number,
        birth_date,
        address,
        phone,
        email,
        user_id
    )
    VALUES (
               p_last_name,
               p_first_name,
               p_middle_name,
               p_passport_series,
               p_passport_number,
               p_birth_date,
               p_address,
               p_phone,
               p_email,
               v_user_id
           )
    RETURNING account_number INTO p_account_number;

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE update_client(
    p_account_number VARCHAR,
    p_last_name VARCHAR,
    p_first_name VARCHAR,
    p_middle_name VARCHAR,
    p_passport_series VARCHAR,
    p_passport_number VARCHAR,
    p_birth_date DATE,
    p_address TEXT,
    p_phone VARCHAR,
    p_email VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE clients
    SET
        last_name = p_last_name,
        first_name = p_first_name,
        middle_name = p_middle_name,
        passport_series = p_passport_series,
        passport_number = p_passport_number,
        birth_date = p_birth_date,
        address = p_address,
        phone = p_phone,
        email = p_email
    WHERE account_number = p_account_number;

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE add_administrator(
    p_login VARCHAR,
    p_password VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO users (login, password, registration_date, role)
    VALUES (p_login, p_password, NOW(), 'ADMINISTRATOR');

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE add_transaction(
    p_credit_id VARCHAR,
    p_amount NUMERIC,
    p_description VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO transactions (transaction_date, credit_id, amount, description)
    VALUES (NOW(), p_credit_id, p_amount, p_description);

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE add_credit_application(
    p_client_id BIGINT,
    p_product_id BIGINT,
    p_manager_id BIGINT,
    p_down_payment NUMERIC,
    p_requested_amount NUMERIC,
    p_term INTEGER,
    p_payment_type VARCHAR,
    OUT p_application_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO applications (
        client_id,
        product_id,
        manager_id,
        application_date,
        down_payment,
        requested_amount,
        term,
        status,
        payment_type
    )
    VALUES (
               p_client_id,
               p_product_id,
               p_manager_id,
               NOW(),
               p_down_payment,
               p_requested_amount,
               p_term,
               'IN_PROGRESS',
               p_payment_type
           )
    RETURNING id INTO p_application_id;

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE update_application_status(
    p_application_id BIGINT,
    p_manager_id BIGINT,
    p_status VARCHAR,
    p_decision VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE applications
    SET
        manager_id = p_manager_id,
        status = p_status,
        decision = p_decision
    WHERE id = p_application_id;

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE add_credit(
    p_application_id BIGINT,
    p_status VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO credits (contract_date, application_id, status)
    VALUES (NOW(), p_application_id, p_status);

    COMMIT;
END;
$$;

CREATE OR REPLACE PROCEDURE update_credit_status(
    p_contract_number BIGINT,
    p_status VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE credits
    SET status = p_status
    WHERE contract_number = p_contract_number;

    COMMIT;
END;
$$;
