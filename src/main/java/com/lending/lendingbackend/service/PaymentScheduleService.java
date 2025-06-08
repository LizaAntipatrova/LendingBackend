package com.lending.lendingbackend.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lending.lendingbackend.data.entity.PaymentType;
import com.lending.lendingbackend.dto.CreditDTO;
import com.lending.lendingbackend.dto.PaymentDTO;
import lombok.SneakyThrows;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PaymentScheduleService {

    // Шрифты с поддержкой русского языка
    private static Font titleFont;
    private static Font headerFont;
    private static Font normalFont;
    private static Font smallFont;
    private static Font boldFont;

    static {
        try {
            // Инициализация русского шрифта (путь к файлу шрифта Times New Roman)
            BaseFont baseFont = BaseFont.createFont(
                    "c:/windows/fonts/times.ttf",  // или другой путь к шрифту
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );

            titleFont = new Font(baseFont, 16, Font.BOLD);
            headerFont = new Font(baseFont, 12, Font.BOLD);
            normalFont = new Font(baseFont, 12);
            smallFont = new Font(baseFont, 10);
            boldFont = new Font(baseFont, 12, Font.BOLD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка загрузки шрифта", e);
        }
    }

    @SneakyThrows
    public static void createPaymentSchedulePdf(OutputStream out, CreditDTO creditDTO) {
        List<PaymentDTO> paymentSchedule = generatePaymentSchedule(creditDTO);
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, out);
        document.open();

        Paragraph bankHeader = new Paragraph();
        bankHeader.setAlignment(Element.ALIGN_CENTER);
        bankHeader.add(new Chunk("ООО \"МикроБанк\"\n", headerFont));
        bankHeader.add(new Chunk("Лицензия ЦБ РФ №1234 от 01.01.2000\n\n", normalFont));
        document.add(bankHeader);

        // Заголовок
        Paragraph documentTitle = new Paragraph("График платежей по кредитному договору №" + creditDTO.getContractNumber(), titleFont);
        documentTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(documentTitle);
        document.add(new Paragraph(" ", headerFont));

        // Таблица
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f, 2f, 2f, 2f, 2f, 2f, 2f, 2f});

        // Заголовки таблицы
        String[] headers = {
                "№", "Рекомендуемая дата", "Крайняя дата",
                "Основной долг", "Проценты", "Ежемесячный платеж",
                "Начисленные проценты", "Остаток долга"
        };

        for (String header : headers) {
            addTableHeader(table, header);
        }

        // Данные
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (PaymentDTO payment : paymentSchedule) {
            table.addCell(new Phrase(String.valueOf(payment.getPaymentNumber()), normalFont));
            table.addCell(new Phrase(payment.getRecommendedDate().format(dateFormatter), normalFont));
            table.addCell(new Phrase(payment.getCriticalDate().format(dateFormatter), normalFont));
            table.addCell(new Phrase(formatMoney(payment.getPrincipalPayment()), normalFont));
            table.addCell(new Phrase(formatMoney(payment.getInterestPayment()), normalFont));
            table.addCell(new Phrase(formatMoney(payment.getTotalPayment()), boldFont));
            table.addCell(new Phrase(formatMoney(payment.getAccruedInterest()), normalFont));
            table.addCell(new Phrase(formatMoney(payment.getRemainingAmount()), normalFont));
        }

        document.add(table);

        // Итог
        BigDecimal total = paymentSchedule.stream()
                .map(PaymentDTO::getTotalPayment)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        document.add(new Paragraph(
                "Общая сумма выплат: " + formatMoney(total),
                boldFont));

        document.close();

    }

    private static void addTableHeader(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new BaseColor(220, 220, 220));
        cell.setPadding(5);
        table.addCell(cell);
    }

    private static String formatMoney(BigDecimal amount) {
        return String.format("%,.2f", amount);
    }

    private static List<PaymentDTO> generatePaymentSchedule(CreditDTO creditDTO) {

        List<PaymentDTO> paymentSchedule = new ArrayList<>();
        BigDecimal remainingAmount = creditDTO.getRequestedAmount();
        if (creditDTO.getDownPayment() != null){
            remainingAmount = creditDTO.getRequestedAmount().subtract(creditDTO.getDownPayment());
        }
        BigDecimal monthlyInterestRate = creditDTO.getInterestRate()
                .divide(new BigDecimal("1200"), 10, BigDecimal.ROUND_HALF_UP);

        // Расчет ежемесячного платежа для аннуитетной схемы
        BigDecimal monthlyPayment = BigDecimal.ZERO;
        if (creditDTO.getPaymentType().equals(PaymentType.ANNUITY)) {
            BigDecimal temp = BigDecimal.ONE.add(monthlyInterestRate).pow(creditDTO.getTerm());
            BigDecimal coefficient = monthlyInterestRate.multiply(temp)
                    .divide(temp.subtract(BigDecimal.ONE), 10, BigDecimal.ROUND_HALF_UP);
            monthlyPayment = coefficient.multiply(remainingAmount)
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        LocalDate currentDate = creditDTO.getContractDate();

        for (int i = 1; i <= creditDTO.getTerm(); i++) {
            PaymentDTO payment = new PaymentDTO();
            payment.setPaymentNumber(i);

            // Расчет дат платежа
            LocalDate dueDate = currentDate.plusMonths(i);
            payment.setCriticalDate(dueDate);
            payment.setRecommendedDate(dueDate.minusDays(4));

            // Расчет процентов
            int daysInYear = dueDate.isLeapYear() ? 366 : 365;
            int daysInMonth = dueDate.lengthOfMonth();
            BigDecimal interest = remainingAmount.multiply(creditDTO.getInterestRate())
                    .multiply(new BigDecimal(daysInMonth))
                    .divide(new BigDecimal(daysInYear).multiply(new BigDecimal(100)), 2, BigDecimal.ROUND_HALF_UP);

            // Расчет платежа по основному долгу и процентам
            if (creditDTO.getPaymentType().equals(PaymentType.ANNUITY)) {
                if (i == creditDTO.getTerm()) {
                    // Последний платеж - корректируем, чтобы остаток был 0
                    payment.setPrincipalPayment(remainingAmount);
                    payment.setInterestPayment(interest);
                    payment.setTotalPayment(payment.getPrincipalPayment().add(payment.getInterestPayment()));
                } else {
                    payment.setPrincipalPayment(monthlyPayment.subtract(interest));
                    payment.setInterestPayment(interest);
                    payment.setTotalPayment(monthlyPayment);
                }
            } else { // Дифференцированный платеж
                payment.setPrincipalPayment(remainingAmount.divide(
                        new BigDecimal(creditDTO.getTerm() - i + 1), 2, BigDecimal.ROUND_HALF_UP));
                if (i == creditDTO.getTerm()) {
                    payment.setPrincipalPayment(remainingAmount);// Последний платеж - весь остаток
                }
                payment.setInterestPayment(interest);
                payment.setTotalPayment(payment.getPrincipalPayment().add(payment.getInterestPayment()));
            }

            // Обновление остатка долга
            payment.setAccruedInterest(interest);
            remainingAmount = remainingAmount.subtract(payment.getPrincipalPayment());

            // Корректировка остатка для последнего платежа
            if (i == creditDTO.getTerm()) {
                remainingAmount = BigDecimal.ZERO;
            }
            payment.setRemainingAmount(remainingAmount.compareTo(BigDecimal.ZERO) >= 0
                    ? remainingAmount : BigDecimal.ZERO);

            paymentSchedule.add(payment);
        }

        return paymentSchedule;
    }
}
