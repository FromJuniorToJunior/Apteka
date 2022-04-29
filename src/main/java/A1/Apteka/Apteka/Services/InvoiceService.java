package A1.Apteka.Apteka.Services;

import A1.Apteka.Apteka.Model.*;
import A1.Apteka.Apteka.Repository.InvoiceRepository;
import A1.Apteka.Apteka.Repository.OrderRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class InvoiceService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private HttpSession session;

    private record MergedRegion(String name, int address) {
    }

    private record CellStyles(String name, CellStyle style) {
    }

    private record Fonts(String name, Font style) {
    }


    public Workbook invoice(Order order) {
        Workbook invoice = new XSSFWorkbook();
        LinkedList<Row> rows = new LinkedList<>();
        LinkedList<MergedRegion> mergedRegion = new LinkedList<>();
        LinkedList<CellStyles> styles = new LinkedList<>();
        LinkedList<Fonts> fonts = new LinkedList<>();
        LinkedList<Cell> cells = new LinkedList<>();
        String[] headers = {"Lp.", "Nazwa (rodzaj) towaru lub usługi (zakres wykonywanych usług)", "Podstawa prawna zwolnienia od podatku",
                "Miara", "Ilość", "Cena jednostkowa bez podatku", "", "Wartość towarów (usług) bez podatku", "", "Stawka podatku", "Kwota podatku", "",
                "Wartość towarów (usług) wraz z podatkiem"};

        Sheet sheet = invoice.createSheet("invoice");

        //#HEADER
        rows.add(sheet.createRow(0));
        rows.add(sheet.createRow(1));
        rows.add(sheet.createRow(2));
        rows.get(0).createCell(0);
        rows.get(1).createCell(0);
        rows.get(2).createCell(0);

        fonts.add(new Fonts("headerFirst", invoice.createFont()));
        fonts.getFirst().style.setBold(true);
        fonts.getFirst().style.setColor(IndexedColors.WHITE.getIndex());

        styles.add(new CellStyles("sprzedawca", invoice.createCellStyle()));
        rows.getFirst().setHeight((short) 500);
        rows.get(1).setHeight((short) 500);
        rows.getLast().setHeight((short) 500);
        sheet.setColumnWidth(0, 900);
        sheet.setColumnWidth(2, 3000);
        styles.getFirst().style.setRotation((short) 90);
        styles.getFirst().style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        styles.getFirst().style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styles.getFirst().style.setFont(fonts.getFirst().style);
        styles.getFirst().style.setVerticalAlignment(VerticalAlignment.CENTER);
        styles.getFirst().style.setAlignment(HorizontalAlignment.CENTER);
        styles.getFirst().style.setWrapText(true);
        rows.getFirst().getCell(0).setCellValue("Sprzedawca");
        rows.getFirst().getCell(0).setCellStyle(styles.getFirst().style());

        mergedRegion.add(new MergedRegion("sprzedawca", sheet.addMergedRegion(new CellRangeAddress(
                0, 2, 0, 0))));

        //#Region2
        for (Row row : rows
        ) {
            row.createCell(1);
            row.createCell(2);
        }
        fonts.add(new Fonts("dane", invoice.createFont()));


        styles.add(new CellStyles("dane", invoice.createCellStyle()));
        styles.get(1).style.setVerticalAlignment(VerticalAlignment.TOP);
        styles.get(1).style.setWrapText(true);


        sheet.setColumnWidth(1, 10000);
        rows.getFirst().getCell(1).setCellStyle(styles.get(1).style);
        rows.getFirst().getCell(1).setCellValue("Podatnik" + System.lineSeparator() + System.lineSeparator()
                + "Adres" + System.lineSeparator() + System.lineSeparator() + "NIP");

        mergedRegion.add(new MergedRegion("dane", sheet.addMergedRegion(new CellRangeAddress(
                0, 2, 1, 2))));

        RegionUtil.setBorderTop(BorderStyle.DASH_DOT_DOT, sheet.getMergedRegion(1), sheet);
        RegionUtil.setBorderBottom(BorderStyle.DASH_DOT_DOT, sheet.getMergedRegion(1), sheet);

        //#Region 3
        fonts.add(new Fonts("nr", invoice.createFont()));
        fonts.getLast().style.setColor(IndexedColors.WHITE.getIndex());
        fonts.getLast().style.setFontHeightInPoints((short) 30);
        fonts.getLast().style.setBold(true);

        styles.add(new CellStyles("nr", invoice.createCellStyle()));
        styles.getLast().style.setBorderLeft(BorderStyle.MEDIUM);
        styles.getLast().style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        styles.getLast().style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styles.getLast().style.setWrapText(true);
        styles.getLast().style.setVerticalAlignment(VerticalAlignment.TOP);
        styles.getLast().style.setFont(fonts.getLast().style());
        styles.getLast().style.setAlignment(HorizontalAlignment.CENTER);

        mergedRegion.add(new MergedRegion("nr", sheet.addMergedRegion(new CellRangeAddress(
                0, 2, 3, 5))));


        RegionUtil.setBorderRight(BorderStyle.MEDIUM, sheet.getMergedRegion(2), sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, sheet.getMergedRegion(2), sheet);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, sheet.getMergedRegion(2), sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, sheet.getMergedRegion(2), sheet);
        RichTextString rech = new XSSFRichTextString("Faktura Nr" + System.lineSeparator()+ order.getNumber());
        rech.applyFont(0,10,fonts.getLast().style());
        fonts.add(new Fonts("nrLower",invoice.createFont()));
        fonts.getLast().style.setColor(IndexedColors.WHITE.getIndex());
        fonts.getLast().style.setFontHeightInPoints((short) 12);
        fonts.getLast().style.setBold(true);
        rech.applyFont(10,34,fonts.getLast().style);

        Cell cell = CellUtil.createCell(rows.get(0), 3,"", styles.getLast().style);
        cell.setCellValue(rech);

        //Region 4

        mergedRegion.add(new MergedRegion("miejscowosc",
                sheet.addMergedRegion(new CellRangeAddress(0, 2, 6, 13))));


        Cell cell1 = CellUtil.createCell(rows.getFirst(), 6,
                "Miejscowość:" + System.lineSeparator() + System.lineSeparator()
                        + "Data wystawienia faktury:" + System.lineSeparator() + System.lineSeparator()
                        + "Data dokonania dostawy:", styles.get(1).style);

        RegionUtil.setBorderRight(BorderStyle.MEDIUM, sheet.getMergedRegion(mergedRegion.getLast().address()), sheet);
        RegionUtil.setBorderBottom(BorderStyle.DASH_DOT, sheet.getMergedRegion(mergedRegion.getLast().address()), sheet);
        RegionUtil.setBorderTop(BorderStyle.DASH_DOT, sheet.getMergedRegion(mergedRegion.getLast().address), sheet);
        // Region 1 line 2
        rows.add(sheet.createRow(3));
        rows.add(sheet.createRow(4));
        rows.add(sheet.createRow(5));
        rows.add(sheet.createRow(6));
        rows.get(4).setHeight((short) 500);
        rows.get(5).setHeight((short) 500);
        rows.get(6).setHeight((short) 500);


        mergedRegion.add(new MergedRegion("nabywca1", sheet.addMergedRegion(new CellRangeAddress(
                4, 6, 0, 0
        ))));

        Cell cell2 = CellUtil.createCell(rows.get(4), 0, "Nabywca", styles.getFirst().style());


        mergedRegion.add(new MergedRegion("nabywca2", sheet.addMergedRegion(new CellRangeAddress(
                4, 6, 1, 13
        ))));

        Cell cell3 = CellUtil.createCell(rows.get(4), 1, System.lineSeparator() + "Imię i nazwisko lub nazwa:"
                + System.lineSeparator() + System.lineSeparator() +
                "Adres:                                                                     " +
                "                                                                                      " +
                "                                                                       NIP:", styles.get(1).style);


        RegionUtil.setBorderBottom(BorderStyle.DASH_DOT, sheet.getMergedRegion(mergedRegion.getLast().address), sheet);
        RegionUtil.setBorderTop(BorderStyle.DASH_DOT, sheet.getMergedRegion(mergedRegion.getLast().address), sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, sheet.getMergedRegion(mergedRegion.getLast().address), sheet);

        rows.add(sheet.createRow(7));

        //#l3
        fonts.add(new Fonts("list", invoice.createFont()));
        fonts.getLast().style.setColor(IndexedColors.WHITE.getIndex());

        styles.add(new CellStyles("list", invoice.createCellStyle()));
        styles.getLast().style.setVerticalAlignment(VerticalAlignment.CENTER);
        styles.getLast().style.setFont(fonts.getLast().style());
        styles.getLast().style.setAlignment(HorizontalAlignment.CENTER);
        styles.getLast().style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        styles.getLast().style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styles.getLast().style.setWrapText(true);


        rows.add(sheet.createRow(8));
        rows.add(sheet.createRow(9));
        rows.add(sheet.createRow(10));
        sheet.getRow(8).setHeight((short) 500);
        sheet.getRow(9).setHeight((short) 500);
        for (int x = 0; x <= 4; x++) {
            mergedRegion.add(new MergedRegion("list" + x, sheet.addMergedRegion(new CellRangeAddress(
                    8, 10, x, x))));

            cells.add(CellUtil.createCell(sheet.getRow(8), x, headers[x], styles.getLast().style()));
            RegionUtil.setBorderRight(BorderStyle.MEDIUM, sheet.getMergedRegion(mergedRegion.getLast().address), sheet);

        }
        for (int x = 5; x <= 13; x += 2) {
            if (x != 9) {
                mergedRegion.add(new MergedRegion("list" + x, sheet.addMergedRegion(new CellRangeAddress(
                        8, 9, x, x + 1))));
            } else {
                mergedRegion.add(new MergedRegion("list" + x, sheet.addMergedRegion(new CellRangeAddress(
                        8, 9, x, x))));
                cells.add(CellUtil.createCell(sheet.getRow(8), x, headers[x], styles.getLast().style()));
                x--;

            }

            cells.add(CellUtil.createCell(sheet.getRow(8), x, headers[x], styles.getLast().style()));


            RegionUtil.setBorderLeft(BorderStyle.MEDIUM, sheet.getMergedRegion(mergedRegion.getLast().address), sheet);

        }
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, sheet.getMergedRegion(mergedRegion.getLast().address), sheet);

        styles.add(new CellStyles("headers2", invoice.createCellStyle()));
        styles.getLast().style.setAlignment(HorizontalAlignment.CENTER);
        // styles.getLast().style.setBottomBorderColor(IndexedColors.AQUA.getIndex());
        // styles.getLast().style.setRightBorderColor(IndexedColors.AQUA.getIndex());
        styles.getLast().style.setBorderRight(BorderStyle.MEDIUM);
        styles.getLast().style.setBorderBottom(BorderStyle.MEDIUM);
        styles.getLast().style.setBorderLeft(BorderStyle.MEDIUM);
        styles.getLast().style.setVerticalAlignment(VerticalAlignment.CENTER);
        styles.getLast().style.setWrapText(true);


        for (int x = 5; x <= 13; x++) {
            if (x != 9) {
                if (x < 10) {
                    if (x % 2 != 0) {
                        CellUtil.createCell(sheet.getRow(10), x, "zł", styles.getLast().style());

                    } else {
                        CellUtil.createCell(sheet.getRow(10), x, "gr", styles.getLast().style());
                    }
                } else {
                    if (x % 2 == 0) {
                        CellUtil.createCell(sheet.getRow(10), x, "zł", styles.getLast().style());

                    } else {
                        CellUtil.createCell(sheet.getRow(10), x, "gr", styles.getLast().style());
                    }
                }
            } else {
                CellUtil.createCell(sheet.getRow(10), x, "[%]", styles.getLast().style());
            }

        }

        styles.add(new CellStyles("body", invoice.createCellStyle()));
        styles.getLast().style.setBorderRight(BorderStyle.MEDIUM);
        styles.getLast().style.setBorderBottom(BorderStyle.MEDIUM);
        styles.getLast().style.setAlignment(HorizontalAlignment.CENTER);
        styles.getLast().style.setVerticalAlignment(VerticalAlignment.CENTER);
        int x = 0;
        for (AnxInOrder anx : order.getAnxInOrders()
        ) {

            int row = rows.size();
            double price = anx.getAnxieties().getPrice() * anx.getAmount();
            double kwotaPodatku = price * anx.getAnxieties().getTaxRate() / 100;
            double cenaJednostkowaBezPodatku = anx.getAnxieties().getPrice() - anx.getAnxieties().getPrice() * ((double) (anx.getAnxieties().getTaxRate()) / 100);

            rows.add(sheet.createRow(row));
            for (int cellNumber = 0; cellNumber < 14; cellNumber++) {
                rows.getLast().createCell(cellNumber).setCellValue(x);
                rows.getLast().getCell(cellNumber).setCellStyle(styles.getLast().style());
                switch (cellNumber) {
                    case 0 -> rows.getLast().getCell(cellNumber).setCellValue(x);
                    case 1 -> rows.getLast().getCell(cellNumber).setCellValue(anx.getAnxieties().getName().toUpperCase());
                    case 2 -> rows.getLast().getCell(cellNumber).setCellValue("Brak");
                    case 3 -> rows.getLast().getCell(cellNumber).setCellValue(anx.getAnxieties().getUnit());
                    case 4 -> rows.getLast().getCell(cellNumber).setCellValue(anx.getAmount());
                    case 5 -> rows.getLast().getCell(cellNumber).setCellValue((cenaJednostkowaBezPodatku + "").substring(0, ("" + cenaJednostkowaBezPodatku).indexOf(".")));
                    case 6 -> rows.getLast().getCell(cellNumber).setCellValue((cenaJednostkowaBezPodatku + "").substring(("" + cenaJednostkowaBezPodatku).indexOf(".") + 1,
                            ("" + cenaJednostkowaBezPodatku).indexOf(".") + 3));
                    case 7 -> rows.getLast().getCell(cellNumber).setCellValue((price - kwotaPodatku + "").substring(0, (+price - kwotaPodatku + "").indexOf(".")));
                    case 8 -> rows.getLast().getCell(cellNumber).setCellValue((price - kwotaPodatku + "").substring((price - cenaJednostkowaBezPodatku + "").indexOf(".") + 1,
                            (price - cenaJednostkowaBezPodatku + "").indexOf(".") + 3));
                    case 9 -> rows.getLast().getCell(cellNumber).setCellValue(anx.getAnxieties().getTaxRate());
                    case 10 -> rows.getLast().getCell(cellNumber).setCellValue((kwotaPodatku + "").substring(0, ("" + kwotaPodatku).indexOf(".")));
                    case 11 -> rows.getLast().getCell(cellNumber).setCellValue((kwotaPodatku + "").substring(("" + kwotaPodatku).indexOf(".") + 1, ("" + kwotaPodatku).indexOf(".") + 3));
                    case 12 -> rows.getLast().getCell(cellNumber).setCellValue((price + "").substring(0, ("" + price).indexOf(".")));
                    case 13 -> rows.getLast().getCell(cellNumber).setCellValue((price + "").substring(("" + price).indexOf(".") + 1));
                }

            }
            x++;


        }

        styles.add(new CellStyles("whiteLast", invoice.createCellStyle()));
        styles.getLast().style.setWrapText(true);
        styles.getLast().style.setAlignment(HorizontalAlignment.RIGHT);
        styles.getLast().style.setVerticalAlignment(VerticalAlignment.BOTTOM);
        styles.add(new CellStyles("dark", invoice.createCellStyle()));
        styles.getLast().style.setWrapText(true);
        styles.getLast().style.setAlignment(HorizontalAlignment.LEFT);
        styles.getLast().style.setVerticalAlignment(VerticalAlignment.TOP);
        styles.add(new CellStyles("dark", invoice.createCellStyle()));
        styles.getLast().style.setWrapText(true);
        styles.getLast().style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        styles.getLast().style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styles.getLast().style.setAlignment(HorizontalAlignment.LEFT);
        styles.getLast().style.setVerticalAlignment(VerticalAlignment.TOP);
        styles.getLast().style.setFont(fonts.getLast().style());

        rows.add(sheet.createRow(rows.size()));
        rows.add(sheet.createRow(rows.size()));
        rows.getLast().setHeight((short) 500);
        rows.add(sheet.createRow(rows.size()));
        rows.getLast().setHeight((short) 500);
        rows.add(sheet.createRow(rows.size()));
        rows.getLast().setHeight((short) 500);
        mergedRegion.add(new MergedRegion("sposobZaplaty", sheet.addMergedRegion(new CellRangeAddress(rows.size() - 3, rows.size() - 1, 0, 5))));
        cells.add(CellUtil.createCell(rows.get(rows.size() - 3), 0,
                "Sposób zapłaty _______________________________ termin zaplaty: __________________" + System.lineSeparator() + System.lineSeparator() +
                        "W Banku: ____________________________________________________________________" + System.lineSeparator() + System.lineSeparator() +
                        "Nr konta: ____________________________________________________________________", styles.getLast().style()));

        RegionUtil.setBorderRight(BorderStyle.MEDIUM, sheet.getMergedRegion(16), sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, sheet.getMergedRegion(16), sheet);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, sheet.getMergedRegion(16), sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, sheet.getMergedRegion(16), sheet);

        rows.add(sheet.createRow(rows.size()));
        rows.add(sheet.createRow(rows.size()));
        rows.getLast().setHeight((short) 500);
        rows.add(sheet.createRow(rows.size()));
        rows.getLast().setHeight((short) 500);
        rows.add(sheet.createRow(rows.size()));
        rows.getLast().setHeight((short) 500);
        mergedRegion.add(new MergedRegion("sposobZaplaty", sheet.addMergedRegion(new CellRangeAddress(rows.size() - 3, rows.size() - 1, 0, 5))));
        cells.add(CellUtil.createCell(rows.get(rows.size() - 3), 0, "Do" + System.lineSeparator() + "zapłaty"
                        + "____________ zł ______ gr słownie: zł/gr"
                        + "_____________________________________"
                        + System.lineSeparator() + System.lineSeparator() + "__________________________________________________________________________"
                        + System.lineSeparator() + System.lineSeparator()
                , styles.get(styles.size() - 2).style));
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, sheet.getMergedRegion(17), sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, sheet.getMergedRegion(17), sheet);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, sheet.getMergedRegion(17), sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, sheet.getMergedRegion(17), sheet);

        rows.add(sheet.createRow(rows.size()));
        rows.add(sheet.createRow(rows.size()));
        rows.getLast().setHeight((short) 500);
        rows.add(sheet.createRow(rows.size()));
        rows.getLast().setHeight((short) 500);
        mergedRegion.add(new MergedRegion("Adnotacje: ", sheet.addMergedRegion(new CellRangeAddress(rows.size() - 2, rows.size() - 1, 0, 5))));
        cells.add(CellUtil.createCell(rows.get(rows.size() - 2), 0, "Adnotacje", styles.getLast().style));

        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, sheet.getMergedRegion(18), sheet);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, sheet.getMergedRegion(18), sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, sheet.getMergedRegion(18), sheet);

        mergedRegion.add(new MergedRegion("sposobZaplaty", sheet.addMergedRegion(new CellRangeAddress(rows.size() - 2, rows.size() - 1, 6, 13))));
        cells.add(CellUtil.createCell(rows.get(rows.size() - 2), 6, "podpis wystawcy faktury", styles.get(styles.size() - 3).style));

        RegionUtil.setBorderRight(BorderStyle.MEDIUM, sheet.getMergedRegion(19), sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, sheet.getMergedRegion(19), sheet);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, sheet.getMergedRegion(19), sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, sheet.getMergedRegion(19), sheet);

        mergedRegion.add(new MergedRegion("zestawienie", sheet.addMergedRegion(new CellRangeAddress(14, 19, 6, 6))));
        cells.add(CellUtil.createCell(sheet.getRow(14), 6, "Zestawienie sprzedaży wg stawek podatku", styles.getFirst().style));

        Cell razem = sheet.getRow(20).createCell(6);
        razem.setCellValue("Razem");
        razem.setCellStyle(styles.get(3).style);
       /* for (CellStyles style: styles
             ) {
            System.out.println(style.name);
        }*/
        String[] txes = {"23 %", "8 %", "5 %", "0 %", "zw."};
        for (int row = 14; row < 21; row++) {
            for (int col = 7; col < 14; col++) {
                sheet.getRow(row).createCell(col).setCellStyle(styles.get(4).style);

                if (col == 9 && row < 19) {
                    sheet.getRow(row).getCell(col).setCellValue(txes[row - 14]);
                }
            }
        }
        double suma23 = 0;
        double suma8 = 0;
        double suma5 = 0;
        double suma0 = 0;
        for (AnxInOrder anx : order.getAnxInOrders()
        ) {
            if (anx.getAnxieties().getTaxRate() == 23) {
                suma23 += anx.getAmount() * anx.getAnxieties().getPrice();
            }
            if (anx.getAnxieties().getTaxRate() == 8) {
                suma8 += anx.getAmount() * anx.getAnxieties().getPrice();
            }
            if (anx.getAnxieties().getTaxRate() == 5) {
                suma5 += anx.getAmount() * anx.getAnxieties().getPrice();
            }
            if (anx.getAnxieties().getTaxRate() == 0) {
                suma0 += anx.getAmount() * anx.getAnxieties().getPrice();
            }
        }
        for (int row = 14; row < 18; row++)
            switch (row) {
                case 14 -> {
                    sheet.getRow(row).getCell(12).setCellValue((new BigDecimal((suma23 + "").substring(0, ("" + suma23).indexOf(".")))).doubleValue());
                    sheet.getRow(row).getCell(13).setCellValue(new BigDecimal((suma23 + "").substring(("" + suma23).indexOf(".") + 1)).doubleValue());
                    sheet.getRow(row).getCell(10).setCellValue(new BigDecimal(((suma23 * 0.23) + "").substring(0, ("" + (suma23 * 0.23)).indexOf("."))).doubleValue());
                    sheet.getRow(row).getCell(11).setCellValue(new BigDecimal(((suma23 * 0.23) + "").substring(("" + (suma23 * 0.23)).indexOf(".") + 1,
                            ("" + (suma23 * 0.23)).indexOf(".") + 2)).doubleValue());
                    sheet.getRow(row).getCell(7).setCellValue(new BigDecimal(((suma23 - suma23 * 0.23) + "").substring(0,
                            ("" + (suma23 - suma23 * 0.23)).indexOf("."))).doubleValue());
                    sheet.getRow(row).getCell(8).setCellValue(new BigDecimal(((suma23 - suma23 * 0.23) + "").substring(("" + (suma23 - suma23 * 0.23)).indexOf(".") + 1,
                            ("" + (suma23 - suma23 * 0.23)).indexOf(".") + 2)).doubleValue());
                }
                case 15 -> {
                    sheet.getRow(row).getCell(12).setCellValue(new BigDecimal((suma8 + "").substring(0, ("" + suma8).indexOf("."))).doubleValue());
                    sheet.getRow(row).getCell(13).setCellValue(new BigDecimal((suma8 + "").substring(("" + suma8).indexOf(".") + 1)).doubleValue());
                    sheet.getRow(row).getCell(10).setCellValue(new BigDecimal(((suma8 * 0.08) + "").substring(0, ("" + (suma8 * 0.08)).indexOf("."))).doubleValue());
                    sheet.getRow(row).getCell(11).setCellValue(new BigDecimal(((suma8 * 0.08) + "").substring(("" + (suma8 * 0.08)).indexOf(".") + 1,
                            ("" + (suma8 * 0.08)).indexOf(".") + 2)).doubleValue());
                    sheet.getRow(row).getCell(7).setCellValue(new BigDecimal(((suma8 - suma8 * 0.08) + "").substring(0,
                            ("" + (suma8 - suma8 * 0.08)).indexOf("."))).doubleValue());
                    sheet.getRow(row).getCell(8).setCellValue(new BigDecimal(((suma8 - suma8 * 0.08) + "").substring(("" + (suma8 - suma8 * 0.08)).indexOf(".") + 1,
                            ("" + (suma8 - suma8 * 0.08)).indexOf(".") + 2)).doubleValue());
                }
                case 16 -> {
                    sheet.getRow(row).getCell(12).setCellValue(new BigDecimal((suma5 + "").substring(0, ("" + suma5).indexOf("."))).doubleValue());
                    sheet.getRow(row).getCell(13).setCellValue(new BigDecimal((suma5 + "").substring(("" + suma5).indexOf(".") + 1)).doubleValue());
                    sheet.getRow(row).getCell(10).setCellValue(new BigDecimal(((suma5 * 0.05) + "").substring(0, ("" + (suma5 * 0.05)).indexOf("."))).doubleValue());
                    sheet.getRow(row).getCell(11).setCellValue(new BigDecimal(((suma5 * 0.05) + "").substring(("" + (suma5 * 0.05)).indexOf(".") + 1,
                            ("" + (suma5 * 0.05)).indexOf(".") + 2)).doubleValue());
                    sheet.getRow(row).getCell(7).setCellValue(new BigDecimal(((suma5 - suma5 * 0.05) + "").substring(0, ("" + (suma5 - suma5 * 0.05)).indexOf(".")))
                            .doubleValue());
                    sheet.getRow(row).getCell(8).setCellValue(new BigDecimal(((suma5 - suma5 * 0.05) + "").substring(("" + (suma5 - suma5 * 0.05)).indexOf(".") + 1,
                            ("" + (suma5 - suma5 * 0.05)).indexOf(".") + 2)).doubleValue());
                }
                case 17 -> {
                    sheet.getRow(row).getCell(12).setCellValue(new BigDecimal((suma0 + "").substring(0, ("" + suma0).indexOf("."))).doubleValue());
                    sheet.getRow(row).getCell(13).setCellValue(new BigDecimal((suma0 + "").substring(("" + suma0).indexOf(".") + 1)).doubleValue());
                    sheet.getRow(row).getCell(7).setCellValue(new BigDecimal(((suma0) + "").substring(0, ("" + (suma0)).indexOf("."))).doubleValue());
                    sheet.getRow(row).getCell(8).setCellValue(new BigDecimal(((suma0) + "").substring(("" + (suma0)).indexOf(".") + 1,
                            ("" + (suma0)).indexOf(".") + 2)).doubleValue());
                }
            }

        for (int col = 7; col < 14; col++) {
            switch (col) {
                case 7 -> sheet.getRow(20).getCell(col).setCellFormula("SUM(H15:H19)");
                case 8 -> sheet.getRow(20).getCell(col).setCellFormula("SUM(I15:I19)");
                case 9 -> sheet.getRow(20).getCell(col).setCellFormula("SUM(J15:J19)");
                case 10 -> sheet.getRow(20).getCell(col).setCellFormula("SUM(K15:K19)");
                case 11 -> sheet.getRow(20).getCell(col).setCellFormula("SUM(L15:L19)");
                case 12 -> sheet.getRow(20).getCell(col).setCellFormula("SUM(M15:M19)");
                case 13 -> sheet.getRow(20).getCell(col).setCellFormula("SUM(N15:N19)");
            }
        }


        return invoice;
    }


    //Save workbook
    public void saveXlsx(Workbook workbook, String name) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(name)) {
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void saveXlsxToDB(Workbook workbook) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
        workbook.write(baos);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        Invoice invoice = new Invoice("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "Invoice"+LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/mm/ss"))+".xlsx" ,
                (User) session.getAttribute("user"), baos.toByteArray());
        invoiceRepository.save(invoice);
    }

}
