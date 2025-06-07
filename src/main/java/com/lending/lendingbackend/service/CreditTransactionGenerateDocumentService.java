package com.lending.lendingbackend.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lending.lendingbackend.data.entity.Transaction;
import com.lending.lendingbackend.exceptions.generate.FontLoadException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditTransactionGenerateDocumentService {

    private static Font titleFont;
    private static Font headerFont;
    private static Font normalFont;
    private static Font tableCellFont;
    private static Font tableHeaderFont;
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


            tableHeaderFont = new Font(baseFont, 10);
            tableCellFont = new Font(baseFont, 10);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FontLoadException();
        }
    }
    @SneakyThrows
    public void generateCreditStatement(OutputStream out,Long contractNumber, List<Transaction> transactions, String managerName) {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);
        document.open();

// Логотип и шапка банка
        Paragraph bankHeader = new Paragraph();
        bankHeader.setAlignment(Element.ALIGN_CENTER);
        bankHeader.add(new Chunk("ООО \"МикроБанк\"\n", headerFont));
        bankHeader.add(new Chunk("Лицензия ЦБ РФ №1234 от 01.01.2000\n\n", normalFont));
        document.add(bankHeader);

        // Название документа
        Paragraph docTitle = new Paragraph("ВЫПИСКА ПО КРЕДИТУ", titleFont);
        docTitle.setAlignment(Element.ALIGN_CENTER);
        docTitle.setSpacingAfter(20);
        document.add(docTitle);

        // Информация о кредите
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        PdfPTable infoTable = new PdfPTable(2);
        infoTable.setWidthPercentage(100);
        infoTable.setSpacingBefore(10);
        infoTable.setSpacingAfter(15);


        infoTable.addCell(createCell("Номер договора:", tableHeaderFont));
        infoTable.addCell(createCell(contractNumber.toString(), tableCellFont));
        infoTable.addCell(createCell("Дата составления:", tableHeaderFont));
        infoTable.addCell(createCell(dateFormat.format(Date.from(Instant.now())), tableCellFont));

        document.add(infoTable);

        // Таблица транзакций
        Paragraph transactionsTitle = new Paragraph("Список транзакций:", titleFont);
        transactionsTitle.setSpacingBefore(10);
        transactionsTitle.setSpacingAfter(10);
        document.add(transactionsTitle);

        PdfPTable transactionsTable = new PdfPTable(3);
        transactionsTable.setWidthPercentage(100);
        transactionsTable.setWidths(new float[]{2, 2, 4});
        transactionsTable.setSpacingBefore(10);
        transactionsTable.setSpacingAfter(20);

        // Заголовки таблицы
        transactionsTable.addCell(createCell("Дата", tableHeaderFont));
        transactionsTable.addCell(createCell("Сумма", tableHeaderFont));
        transactionsTable.addCell(createCell("Тип транзакции", tableHeaderFont));

        // Данные транзакций
        for (Transaction transaction : transactions) {
            transactionsTable.addCell(createCell(transaction.getTransactionDate().toString(), tableCellFont));
            transactionsTable.addCell(createCell(String.format("%.2f руб.", transaction.getAmount()), tableCellFont));
            transactionsTable.addCell(createCell(transaction.getDescription().getRussianName(), tableCellFont));
        }

        document.add(transactionsTable);

        // Итоговая сумма
        double totalAmount = transactions.stream().mapToDouble(transaction -> transaction.getAmount().doubleValue()).sum();
        Paragraph total = new Paragraph(String.format("Итого: %.2f руб.", totalAmount), titleFont);
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);

        // Подпись
        Paragraph signature = new Paragraph("\n\nОтветственное лицо: _________________ /" + managerName, normalFont);
        signature.setAlignment(Element.ALIGN_RIGHT);
        document.add(signature);

        document.close();
    }

    private static PdfPCell createCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        return cell;
    }

}
