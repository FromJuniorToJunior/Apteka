package A1.Apteka.Apteka.Services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InvoiceService {
    private record MergedRegion(String name, int address) {
    }

    private record CellStyles(String name, CellStyle style) {
    }

    private record Fonts(String name, Font style) {
    }


    private Workbook invoice() {
        Workbook invoice = new XSSFWorkbook();
        LinkedList<Row> rows = new LinkedList<>();
        LinkedList<MergedRegion> mergedRegion = new LinkedList<>();
        LinkedList<CellStyles> styles = new LinkedList<>();
        LinkedList<Fonts> fonts = new LinkedList<>();
        LinkedList<Cell> cells = new LinkedList<>();
        String[] headers = {"Lp.","Nazwa (rodzaj) towaru lub usługi (zakres wykonywanych usług)","Podstawa prawna zwolnienia od podatku",
        "Miara","Ilość", "Cena jednostkowa bez podatku","","Wartość towarów (usług) bez podatku","","Stawka podatku","Kwota podatku","",
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

        Cell cell = CellUtil.createCell(rows.get(0), 3, "Faktura Nr", styles.getLast().style);

        //Region 4

        mergedRegion.add(new MergedRegion("miejscowosc",
                sheet.addMergedRegion(new CellRangeAddress(0, 2, 6, 13))));


        Cell cell1 = CellUtil.createCell(rows.getFirst(), 6,
                "Miejscowość:" + System.lineSeparator() + System.lineSeparator()
                        + "Data wystawienia faktury:" + System.lineSeparator() + System.lineSeparator()
                        + "Data dokonania dostawy:", styles.get(1).style);

        RegionUtil.setBorderRight(BorderStyle.MEDIUM, sheet.getMergedRegion(mergedRegion.getLast().address()), sheet);
        RegionUtil.setBorderBottom(BorderStyle.DASH_DOT, sheet.getMergedRegion(mergedRegion.getLast().address()), sheet);
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
            RegionUtil.setBorderLeft(BorderStyle.MEDIUM, sheet.getMergedRegion(mergedRegion.getLast().address), sheet);

        }
        for (int x = 5; x <= 13; x += 2) {
            if (x != 9) {
                mergedRegion.add(new MergedRegion("list" + x, sheet.addMergedRegion(new CellRangeAddress(
                        8, 9, x, x + 1))));
            }else {
                mergedRegion.add(new MergedRegion("list" + x, sheet.addMergedRegion(new CellRangeAddress(
                        8, 9, x, x))));
                cells.add(CellUtil.createCell(sheet.getRow(8), x, headers[x], styles.getLast().style()));
                x--;

            }

            cells.add(CellUtil.createCell(sheet.getRow(8), x, headers[x], styles.getLast().style()));


            RegionUtil.setBorderLeft(BorderStyle.MEDIUM, sheet.getMergedRegion(mergedRegion.getLast().address), sheet);

        }
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, sheet.getMergedRegion(mergedRegion.getLast().address), sheet);

        styles.add(new CellStyles("headers2",invoice.createCellStyle()));
        styles.getLast().style.setAlignment(HorizontalAlignment.CENTER);
        styles.getLast().style.setBottomBorderColor(IndexedColors.AQUA.getIndex());
        styles.getLast().style.setRightBorderColor(IndexedColors.AQUA.getIndex());
        styles.getLast().style.setBorderRight(BorderStyle.MEDIUM);
        styles.getLast().style.setBorderBottom(BorderStyle.MEDIUM);

        for (int x = 5; x <= 13; x ++) {
            if(x!=9){
            if (x %2!=0) {
             CellUtil.createCell(sheet.getRow(10),x,"zł",styles.getLast().style());

            }else {
                CellUtil.createCell(sheet.getRow(10),x,"gr",styles.getLast().style());
            }}else{
                CellUtil.createCell(sheet.getRow(10),x,"[%]",styles.getLast().style());
            }

        }




        return invoice;
    }


    //Save workbook
    private void saveXlsx(Workbook workbook, String name) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(name)) {
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static void main(String[] args) {

        InvoiceService service = new InvoiceService();
        service.saveXlsx(service.invoice(), "test.xlsx");


    }
}
