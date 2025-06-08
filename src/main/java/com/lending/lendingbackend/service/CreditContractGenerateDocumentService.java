package com.lending.lendingbackend.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lending.lendingbackend.dto.CreditDTO;
import com.lending.lendingbackend.exceptions.generate.FontLoadException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CreditContractGenerateDocumentService {

    private static Font titleFont;
    private static Font headerFont;
    private static Font normalFont;
    static {
        try {
            // Инициализация русского шрифта (путь к файлу шрифта Times New Roman)
            BaseFont baseFont = BaseFont.createFont(
                    "src/main/resources/fonts/times.ttf",
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );

            titleFont = new Font(baseFont, 16, Font.BOLD);
            headerFont = new Font(baseFont, 12);
            normalFont = new Font(baseFont, 12);


        } catch (Exception e) {
            e.printStackTrace();
            throw new FontLoadException();
        }
    }
    @SneakyThrows
    public void generateCreditContract(OutputStream out, CreditDTO creditDTO) {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);
        document.open();


        // Данные договора (можно вынести в параметры метода)
        String contractNumber = creditDTO.getContractNumber().toString();
        String contractDate = creditDTO.getContractDate() == null?
                LocalDate.now().toString()
                :creditDTO.getContractDate().toString();
        String bankName = "ООО «МикроБанк»";
        String managerName = creditDTO.getManagerName();
        String clientName = creditDTO.getClientName();
        String clientPassport = creditDTO.getClientPassportNumber() + " " + creditDTO.getClientPassportSeries();
        String loanAmount = creditDTO.getRequestedAmount().toString();
        String interestRate = creditDTO.getInterestRate().toString();
        String loanTerm = creditDTO.getTerm() + " месяцев";
        String bankAddress = "109240, г. Москва, ул. Верхняя Радищевская, д.18, стр. 2";
        String bankDetails = "ИНН: 7711068778, БИК: 044525440";
        String clientAddress = creditDTO.getClientAddress();

        // Заголовок договора
        Paragraph title = new Paragraph("КРЕДИТНЫЙ ДОГОВОР № " + contractNumber, titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph subTitle = new Paragraph("с физическим лицом", normalFont);
        subTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(subTitle);

        addEmptyLine(document, 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // Дата и место заключения
        Paragraph datePlace = new Paragraph("г. Рязань "+ LocalDate.now().format(formatter), normalFont);
        datePlace.setAlignment(Element.ALIGN_CENTER);
        document.add(datePlace);

        addEmptyLine(document, 2);

        // Преамбула
        Paragraph preamble1 = new Paragraph(
                bankName + ", именуемый в дальнейшем «Банк», в лице " + managerName + ", действующего на основании Устава, " +
                        "с одной стороны и", normalFont);
        document.add(preamble1);

        Paragraph preamble2 = new Paragraph(
                "гр. РФ " + clientName + ", именуемый в дальнейшем «Заемщик», " +
                        "с другой стороны, в дальнейшем именуемые «Стороны», заключили настоящий Договор " +
                        "о нижеследующем:", normalFont);
        document.add(preamble2);

        addEmptyLine(document, 1);

        // Раздел 1. ПРЕДМЕТ ДОГОВОРА
        Paragraph section1Title = new Paragraph("1. ПРЕДМЕТ ДОГОВОРА", headerFont);
        document.add(section1Title);

        Paragraph section1Content = new Paragraph(
                "1.1. Банк предоставляет Заемщику в порядке и на условиях, предусмотренных " +
                        "настоящим Договором, кредит в сумме " + loanAmount + " рублей 00 копеек с уплатой " +
                        "процентов за пользование кредитом из расчета " + interestRate + "% годовых.", normalFont);
        document.add(section1Content);

        Paragraph section1_2 = new Paragraph(
                "1.2. Кредит предоставляется на потребительские цели и может быть использован " +
                        "на другие цели только по дополнительному соглашению Сторон.", normalFont);
        document.add(section1_2);

        addEmptyLine(document, 1);

        // Раздел 2. ИНДИВИДУАЛЬНЫЕ УСЛОВИЯ ДОГОВОРА
        Paragraph section2Title = new Paragraph("2. ИНДИВИДУАЛЬНЫЕ УСЛОВИЯ ДОГОВОРА", headerFont);
        document.add(section2Title);
        addEmptyLine(document, 1);

        // Создаем таблицу для индивидуальных условий
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1, 3});

        // Заголовок таблицы
        addTableHeader(table, "Условие", "Содержание условия");

        // Строки таблицы
        addTableRow(table, "1. Сумма кредита", loanAmount + " рублей");
        addTableRow(table, "2. Срок действия договора", loanTerm);
        addTableRow(table, "3. Валюта кредита", "Рубль");
        addTableRow(table, "4. Процентная ставка", interestRate + "% годовых");
        addTableRow(table, "5. Порядок погашения", "Аннуитетными платежами согласно графику");

        document.add(table);
        addEmptyLine(document, 1);
        // Раздел 3. Порядок предоставления кредита
        Paragraph section3Title = new Paragraph("3. ПОРЯДОК ПРЕДОСТАВЛЕНИЯ КРЕДИТА", headerFont);
        document.add(section3Title);

        Paragraph section3_1 = new Paragraph(
                "3.1. Под датой выдачи понимается день, когда соответствующая сумма " +
                        "зачислена Банком на текущий счет Заемщика, перечислена по указанным им " +
                        "реквизитам или выдана наличными из кассы Банка в корреспонденции со " +
                        "ссудным счетом.", normalFont);
        document.add(section3_1);

        Paragraph section3_2 = new Paragraph("3.2. Кредит не может быть использован на:", normalFont);
        document.add(section3_2);

        // Список ограничений
        com.itextpdf.text.List list31 = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
        list31.add(new ListItem("оплату собственных векселей; выдачу и погашение займов;", normalFont));
        list31.add(new ListItem("оплату платежей, предусмотренных настоящим Кредитным договором, " +
                "иными кредитными договорами, заключенными с Банком или другими кредитными организациями;", normalFont));
        list31.add(new ListItem("погашение обязательств других заемщиков перед Банком;", normalFont));
        list31.add(new ListItem("приобретение у Банка имущества, полученного Банком в результате " +
                "прекращения обязательств Заемщика по ранее предоставленным ссудам предоставлением отступного;", normalFont));
        list31.add(new ListItem("приобретение и погашение эмиссионных ценных бумаг;", normalFont));
        list31.add(new ListItem("осуществление вложений в уставные капиталы юридических лиц.", normalFont));
        document.add(list31);

        addEmptyLine(document, 1);

        // Раздел 4. Проценты
        Paragraph section4Title = new Paragraph("4. ПРОЦЕНТЫ", headerFont);
        document.add(section4Title);

        Paragraph section4_1 = new Paragraph(
                "4.1. Заемщик обязан уплатить Банку проценты по кредиту в размере, " +
                        "указанном в Индивидуальных условиях настоящего Договора.", normalFont);
        document.add(section4_1);

        Paragraph section4_2 = new Paragraph(
                "4.2. Начисление процентов за пользование кредитом начинается с календарного " +
                        "дня, следующего за днем выдачи кредита, и заканчивается календарным днем " +
                        "погашения всей задолженности по кредиту, отраженной на счете по учету ссудной " +
                        "задолженности Заемщика перед Банком. Начисление процентов производится исходя " +
                        "из фактического наличия календарных дней в году (365/366). Начисление процентов " +
                        "производится на сумму задолженности по кредиту, которая определена на начало " +
                        "операционного дня.", normalFont);
        document.add(section4_2);

        Paragraph section4_3 = new Paragraph("Вариант 1:", normalFont);
        document.add(section4_3);

        Paragraph section4_3_1 = new Paragraph(
                "4.3. Процентный период - календарный период, в течение которого начисляются и за " +
                        "который Заемщик уплачивает проценты.", normalFont);
        document.add(section4_3_1);

        Paragraph section4_3_2 = new Paragraph(
                "Первый процентный период по кредиту начинается со дня, следующего за днем выдачи " +
                        "кредита Заемщику и заканчивается в последний день месяца выдачи кредита. Выплата " +
                        "процентов за первый процентный период производится Заемщиком в последний рабочий " +
                        "день месяца предоставления кредита.", normalFont);
        document.add(section4_3_2);

        Paragraph section4_3_3 = new Paragraph(
                "Продолжительность каждого последующего процентного периода соответствует " +
                        "фактическому количеству календарных дней в месяце.", normalFont);
        document.add(section4_3_3);

        addEmptyLine(document, 1);

        // Раздел 5. Порядок пользования кредитом и его возврата
        Paragraph section5Title = new Paragraph("5. ПОРЯДОК ПОЛЬЗОВАНИЯ КРЕДИТОМ И ЕГО ВОЗВРАТА", headerFont);
        document.add(section5Title);

        Paragraph section5_1 = new Paragraph(
                "5.1. Полное погашение кредита производится Заемщиком не позднее дня, " +
                        "указанного в п.2 Индивидуальных условий настоящего Договора.", normalFont);
        document.add(section5_1);

        Paragraph section5_2 = new Paragraph(
                "5.2. При несвоевременном внесении (перечислении) денежных средств в цели " +
                        "возврата кредита и уплаты процентов за пользование кредитом, Заемщик уплачивает " +
                        "Банку неустойку в размере и в сроке определенные в Индивидуальных условиях " +
                        "настоящего Договора.", normalFont);
        document.add(section5_2);

        Paragraph section5_3 = new Paragraph(
                "5.3. Неустойка начисляется и уплачивается за весь период допущенного нарушения. " +
                        "Неустойка за несвоевременное перечисление платежа в погашение кредита, уплату " +
                        "процентов за пользование кредитом вносится в валюте кредита.", normalFont);
        document.add(section5_3);

        Paragraph section5_4 = new Paragraph(
                "5.4. Датой исполнения Заемщиком денежных обязательств по настоящему Договору " +
                        "является дата зачисления денежных средств на соответствующий счет Банка, на " +
                        "котором отражается задолженность Заемщика перед Банком.", normalFont);
        document.add(section5_4);

        Paragraph section5_5 = new Paragraph(
                "5.5. Сумма произведенного Заемщиком платежа по настоящему Договору в случае, " +
                        "если она недостаточна для полного исполнения обязательств Заемщика по Договору, " +
                        "погашает задолженность Заемщика в следующей очередности:", normalFont);
        document.add(section5_5);

        // Нумерованный список
        com.itextpdf.text.List list51 = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
        list51.add(new ListItem("задолженность по процентам;", normalFont));
        list51.add(new ListItem("задолженность по основному долгу;", normalFont));
        list51.add(new ListItem("неустойка (штраф, пеня);", normalFont));
        list51.add(new ListItem("проценты, начисленные за текущий период платежей;", normalFont));
        list51.add(new ListItem("сумма основного долга за текущий период платежей;", normalFont));
        list51.add(new ListItem("иные платежи, предусмотренные действующим законодательством или настоящим Договором.", normalFont));
        document.add(list51);

        addEmptyLine(document, 1);

// 6.1 Обязанности Банка
        Paragraph section6_1_title = new Paragraph("6.1. Банк обязуется:", normalFont);
        document.add(section6_1_title);

        Paragraph bankObligation1 = new Paragraph("6.1.1. произвести выдачу кредита в соответствии с условиями настоящего Договора;", normalFont);
        document.add(bankObligation1);

        Paragraph bankObligation2 = new Paragraph("6.1.2. возвращать излишне взысканные проценты и излишне полученные платежи по кредиту;", normalFont);
        document.add(bankObligation2);

        Paragraph bankObligation3 = new Paragraph("6.1.3. направлять Заемщику способом, установленным Индивидуальными условиями настоящего Договора, "
                + "информацию о наличии просроченной задолженности по настоящему Договору, в срок не позднее 7 (Семи) календарных дней "
                + "с даты возникновения просроченной задолженности;", normalFont);
        document.add(bankObligation3);

        Paragraph bankObligation4 = new Paragraph("6.1.4. после заключения настоящего Договора Банк обязан обеспечить Заемщику доступ к следующим сведениям:", normalFont);
        document.add(bankObligation4);

        Paragraph bankObligationSub1 = new Paragraph("1) размер текущей задолженности заемщика перед кредитором по договору потребительского кредита (займа);", normalFont);
        bankObligationSub1.setIndentationLeft(20);
        document.add(bankObligationSub1);

        Paragraph bankObligationSub2 = new Paragraph("2) даты и размеры произведенных и предстоящих платежей заемщика по договору потребительского кредита (займа);", normalFont);
        bankObligationSub2.setIndentationLeft(20);
        document.add(bankObligationSub2);

        Paragraph bankObligationSub3 = new Paragraph("3) иные сведения, указанные в договоре потребительского кредита (займа).", normalFont);
        bankObligationSub3.setIndentationLeft(20);
        document.add(bankObligationSub3);

        Paragraph bankObligation5 = new Paragraph("Данная информация предоставляется Банком бесплатно при личном обращении Заемщика в Банк.", normalFont);
        bankObligation5.setIndentationLeft(20);
        document.add(bankObligation5);

        addEmptyLine(document, 1);

// 6.2 Права Банка
        Paragraph section6_2_title = new Paragraph("6.2. Банк имеет право:", normalFont);
        document.add(section6_2_title);

        Paragraph bankRight1 = new Paragraph("6.2.1. проводить проверку любых сведений, указанных в Заявлении на кредит и Анкете Заемщика;", normalFont);
        document.add(bankRight1);

        Paragraph bankRight2 = new Paragraph("6.2.2. потребовать от Заемщика досрочно возвратить всю сумму кредита и уплатить причитающиеся проценты "
                + "за пользование кредитом, неустойку, предусмотренные условиями настоящего Договора в случае:", normalFont);
        document.add(bankRight2);

        Paragraph bankRightSub1 = new Paragraph("- неисполнения или ненадлежащего исполнения Заемщиком его обязательств по возврату кредита и/или "
                + "уплате процентов за пользование кредитом по настоящему Договору продолжительностью (общей продолжительностью) более чем "
                + "60 (Шестьдесят) календарных дней в течение последних 180 (Ста восьмидесяти) календарных дней;", normalFont);
        bankRightSub1.setIndentationLeft(30);
        document.add(bankRightSub1);

        Paragraph bankRightSub2 = new Paragraph("- в случае неисполнения Заемщиком свыше 30 (Тридцати) календарных дней обязанности по страхованию заложенного имущества;", normalFont);
        bankRightSub2.setIndentationLeft(30);
        document.add(bankRightSub2);

        Paragraph bankRightSub3 = new Paragraph("- в случае нарушения Заемщиком обязанности целевого использования кредита;", normalFont);
        bankRightSub3.setIndentationLeft(30);
        document.add(bankRightSub3);

        Paragraph bankRight3 = new Paragraph("6.2.3. потребовать от Заемщика досрочно возвратить всю сумму кредита, уплатить причитающиеся проценты "
                + "за пользование кредитом и исполнить иные обязательства, предусмотренные условиями настоящего Договора, в случае утраты "
                + "обеспечения или ухудшения его характеристик;", normalFont);
        document.add(bankRight3);

        Paragraph bankRight4 = new Paragraph("6.2.4. в одностороннем порядке производить по своему усмотрению снижение размера неустойки и/или "
                + "устанавливать период времени, в течение которого неустойка не взимается;", normalFont);
        document.add(bankRight4);

        Paragraph bankRight5 = new Paragraph("6.2.5. осуществлять уступку прав (требований) по настоящему Договору третьим лицам;", normalFont);
        document.add(bankRight5);

        Paragraph bankRight6 = new Paragraph("6.2.6. при досрочном возврате Заемщиком всей суммы кредита или ее части в соответствии с условиями "
                + "настоящего Договора Банк в течение 5 (Пяти) календарных дней со дня получения уведомления исходя из досрочно возвращаемой "
                + "суммы кредита обязан произвести расчет суммы основного долга и процентов за фактический срок пользования кредитом;", normalFont);
        document.add(bankRight6);

        addEmptyLine(document, 1);

// 6.3 Обязанности Заемщика
        Paragraph section6_3_title = new Paragraph("6.3. Заемщик обязуется:", normalFont);
        document.add(section6_3_title);

        Paragraph borrowerObligation1 = new Paragraph("6.3.1. использовать кредит исключительно на цели кредитования, предусмотренные Индивидуальными "
                + "условиями настоящего Договора;", normalFont);
        document.add(borrowerObligation1);

        Paragraph borrowerObligation2 = new Paragraph("6.3.2. окончательное погашение суммы кредита произвести не позднее срока, указанного в "
                + "Индивидуальных условиях настоящего Договора;", normalFont);
        document.add(borrowerObligation2);

        Paragraph borrowerObligation3 = new Paragraph("6.3.3. в срок не позднее 5 (Пяти) рабочих дней уведомлять Банк об изменении фактического "
                + "места жительства, адреса регистрации, работы, фамилии и/или имени, паспортных данных;", normalFont);
        document.add(borrowerObligation3);

        Paragraph borrowerObligation4 = new Paragraph("6.3.4. в случае изменения данных предоставлять Банку информацию о своем финансовом положении, "
                + "имуществе и другие данные, в форме Анкеты заемщика;", normalFont);
        document.add(borrowerObligation4);

        Paragraph borrowerObligation5 = new Paragraph("6.3.5. ежегодно (не позднее 25 января следующего года) предоставлять Банку заверенные "
                + "работодателем справку с места работы и справку о доходах физического лица;", normalFont);
        document.add(borrowerObligation5);

        Paragraph borrowerObligation6 = new Paragraph("6.3.6. отвечать по своим обязательствам перед Банком всем своим имуществом в пределах "
                + "задолженности по кредиту;", normalFont);
        document.add(borrowerObligation6);

        Paragraph borrowerObligation7 = new Paragraph("6.3.7. по требованию Банка в срок, указанный в соответствующем уведомлении, возвратить всю "
                + "сумму кредита и уплатить причитающиеся проценты за пользование кредитом, неустойку;", normalFont);
        document.add(borrowerObligation7);

        Paragraph borrowerObligation8 = new Paragraph("6.3.8. не переуступать полностью или частично свои права и обязанности по настоящему Договору "
                + "другому лицу без согласия Банка.", normalFont);
        document.add(borrowerObligation8);

        addEmptyLine(document, 1);

// 6.4 Заключительные положения
        Paragraph section6_4 = new Paragraph("6.4. Обязанности Заемщика считаются надлежаще и полностью выполненными после возврата Банку всей суммы "
                + "кредита, уплаты процентов за пользование кредитом, неустойки в соответствии с условиями настоящего Договора.", normalFont);
        document.add(section6_4);

// 6.5 Права Заемщика
        Paragraph section6_5_title = new Paragraph("6.5. Заемщик имеет право:", normalFont);
        document.add(section6_5_title);

        Paragraph borrowerRight1 = new Paragraph("6.5.1. получить кредит в порядке, предусмотренном настоящим Договором;", normalFont);
        document.add(borrowerRight1);

        Paragraph borrowerRight2 = new Paragraph("6.5.2. производить досрочный возврат кредита и уплату процентов за пользование им;", normalFont);
        document.add(borrowerRight2);

        Paragraph borrowerRight3 = new Paragraph("6.5.3. отказаться от получения кредита полностью или частично, уведомив об этом Банк до момента "
                + "фактического получения денежных средств;", normalFont);
        document.add(borrowerRight3);

        Paragraph borrowerRight4 = new Paragraph("6.5.4. в течение 14 (Четырнадцати) календарных дней с даты получения кредита имеет право досрочно "
                + "вернуть всю сумму кредита без предварительного уведомления Банка с уплатой процентов за фактический срок кредитования;", normalFont);
        document.add(borrowerRight4);

        Paragraph borrowerRight5 = new Paragraph("6.5.5. вернуть досрочно Банку всю сумму полученного кредита или ее часть, предварительно уведомив "
                + "об этом Банк в письменном виде в произвольной форме.", normalFont);
        document.add(borrowerRight5);

        addEmptyLine(document, 1);

        // Раздел 7. Прочие условия
        Paragraph section7Title = new Paragraph("7. ПРОЧИЕ УСЛОВИЯ", headerFont);
        document.add(section7Title);

        Paragraph section7_1 = new Paragraph(
                "7.1. Стороны несут ответственность за неисполнение и за ненадлежащее " +
                        "исполнение настоящего Договора в соответствии с действующим законодательством " +
                        "и условиями настоящего Договора.", normalFont);
        document.add(section7_1);

        Paragraph section7_2 = new Paragraph(
                "7.2. Заемщик подтверждает, что все условия настоящего Договора с ним " +
                        "согласованы и ни один из его пунктов не ущемляет его законных прав и интересов.", normalFont);
        document.add(section7_2);

        Paragraph section7_3 = new Paragraph(
                "7.3. Любое уведомление или сообщение, направляемое Сторонами друг другу по " +
                        "настоящему Договору, должно быть совершено на русском языке в письменной форме. " +
                        "Такое уведомление или сообщение считается направленным надлежащим образом, если оно " +
                        "доставлено адресату посыльным (курьером), заказным письмом по адресу для корреспонденции, " +
                        "указанному в настоящем Договоре, факсимильной связью (с обязательным предоставлением оригинала " +
                        "в течение 5 (Пяти) рабочих дней) и заверено подписями уполномоченных лиц и печатью Сторон.", normalFont);
        document.add(section7_3);

        Paragraph section7_4 = new Paragraph(
                "7.4. Изменение условий настоящего Договора оформляется дополнительным " +
                        "соглашением, которое является его неотъемлемой частью, если иное не оговорено " +
                        "в настоящем Договоре.", normalFont);
        document.add(section7_4);

        Paragraph section7_5 = new Paragraph(
                "7.5. Все споры, которые могут возникнуть из настоящего Договора или в " +
                        "связи с ним, Стороны будут пытаться решать путем проведения переговоров.", normalFont);
        document.add(section7_5);

        Paragraph section7_6 = new Paragraph(
                "7.6. Если Сторонам не удастся решить спор по договоренности, то любой " +
                        "спор, разногласие или требование, возникающее из настоящего Договора или " +
                        "касающиеся его либо его нарушения, прекращения или недействительности, " +
                        "по искам Заемщика к Банку подлежат рассмотрению в суде общей юрисдикции " +
                        "в соответствии с действующим законодательством, а по искам Банка к Заемщику " +
                        "по месту получения Заемщиком оферты (предложения заключить настоящий Договор) - " +
                        "в суде общей юрисдикции по месту нахождения Банка.", normalFont);
        document.add(section7_6);

        Paragraph section7_7 = new Paragraph(
                "7.7. Во всем остальном, что прямо не предусмотрено настоящим Договором, " +
                        "стороны руководствуются законодательством РФ.", normalFont);
        document.add(section7_7);

        Paragraph section7_8 = new Paragraph(
                "7.8. Настоящий Договор составлен в двух экземплярах, имеющих одинаковую " +
                        "юридическую силу: один экземпляр для Банка, второй для Заемщика.", normalFont);
        document.add(section7_8);
        addEmptyLine(document, 1);

// Персональные данные
        Paragraph pdTitle = new Paragraph("Персональные данные:", normalFont);
        document.add(pdTitle);
        addEmptyLine(document, 1);
        Paragraph pdText = new Paragraph(
                "Подписывая настоящий Договор, Заемщик выражает свое согласие на осуществление " +
                        "Банком обработки (сбора, систематизации, накопления, хранения, уточнения, " +
                        "использования, передачи (в том числе трансграничную), обезличивания, блокирования " +
                        "и уничтожения) его персональных данных, предоставленных Банку, любым способом в " +
                        "соответствии требованиями Федерального закона от 27.07.2006 г. №152-ФЗ «О персональных данных».", normalFont);
        document.add(pdText);
        addEmptyLine(document, 1);
        Paragraph creditHistory = new Paragraph(
                "Заемщик также уведомляется, что в соответствии c ч. 4 ст. 5 Федерального " +
                        "закона от 30.12.2004 г. № 218-ФЗ «О кредитных историях», информация о сведениях, " +
                        "определенных ст. 4 этого закона, передается в бюро кредитных историй в порядке, " +
                        "установленном действующим законодательством Российской Федерации и нормативно-правовыми " +
                        "актами Банка России.", normalFont);
        document.add(creditHistory);

        addEmptyLine(document, 1);

        // Раздел 8. АДРЕСА И РЕКВИЗИТЫ СТОРОН
        Paragraph section8Title = new Paragraph("8. АДРЕСА И РЕКВИЗИТЫ СТОРОН", headerFont);
        document.add(section8Title);
        addEmptyLine(document, 1);

        PdfPTable detailsTable = new PdfPTable(2);
        detailsTable.setWidthPercentage(100);

        // Банк
        PdfPCell bankCell = new PdfPCell();
        bankCell.addElement(new Paragraph("БАНК:", normalFont));
        bankCell.addElement(new Paragraph(bankName, normalFont));
        bankCell.addElement(new Paragraph("Адрес: " + bankAddress, normalFont));
        bankCell.addElement(new Paragraph(bankDetails, normalFont));
        detailsTable.addCell(bankCell);

        // Заемщик
        PdfPCell clientCell = new PdfPCell();
        clientCell.addElement(new Paragraph("ЗАЕМЩИК:", normalFont));
        clientCell.addElement(new Paragraph(clientName, normalFont));
        clientCell.addElement(new Paragraph("Паспорт: " + clientPassport, normalFont));
        clientCell.addElement(new Paragraph("Адрес: " + clientAddress, normalFont));
        detailsTable.addCell(clientCell);

        document.add(detailsTable);

        addEmptyLine(document, 2);

        // Подписи сторон
        Paragraph signaturesTitle = new Paragraph("9. ПОДПИСИ СТОРОН:", headerFont);
        document.add(signaturesTitle);
        addEmptyLine(document, 1);

        PdfPTable signaturesTable = new PdfPTable(2);
        signaturesTable.setWidthPercentage(100);

        PdfPCell bankSignCell = new PdfPCell(new Paragraph("БАНК:____________", normalFont));
        bankSignCell.setBorder(Rectangle.NO_BORDER);
        signaturesTable.addCell(bankSignCell);

        PdfPCell clientSignCell = new PdfPCell(new Paragraph("ЗАЕМЩИК:____________", normalFont));
        clientSignCell.setBorder(Rectangle.NO_BORDER);
        clientSignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        signaturesTable.addCell(clientSignCell);

        document.add(signaturesTable);

        document.close();
    }

    private static void addTableHeader(PdfPTable table, String header1, String header2) {
        PdfPCell cell1 = new PdfPCell(new Paragraph(header1, normalFont));
        PdfPCell cell2 = new PdfPCell(new Paragraph(header2, normalFont));
        table.addCell(cell1);
        table.addCell(cell2);
    }

    private static void addTableRow(PdfPTable table, String condition, String content) {
        table.addCell(new Paragraph(condition, normalFont));
        table.addCell(new Paragraph(content, normalFont));
    }

    private static void addEmptyLine(Document document, int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            document.add(new Paragraph(" "));
        }
    }

}
