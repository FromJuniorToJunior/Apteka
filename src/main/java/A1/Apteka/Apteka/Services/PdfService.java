package A1.Apteka.Apteka.Services;

import A1.Apteka.Apteka.Model.AnxInOrder;
import A1.Apteka.Apteka.Model.Order;
import com.lowagie.text.*;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.VerticalPositionMark;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.PaperSize;
import org.springframework.stereotype.Service;

import java.awt.print.Paper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Service
public class PdfService {

    public void appendDocument() {
        try (PdfReader reader = new PdfReader("test.pdf")) {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, baos);

            PdfPTable table = new PdfPTable(2);
            table.addCell("asd");
            table.addCell("asd");
            table.addCell("asd");
            table.addCell("asd");

            stamper.addAnnotation(PdfAnnotation.createText(stamper.getWriter(), new Rectangle(30f, 750f, 80f, 800f), "test",
                    "Testowy Content", true, null), 1);
            stamper.getUnderContent(1);
            ColumnText.showTextAligned(stamper.getUnderContent(1), Element.ALIGN_CENTER, new Phrase("1/1"), 550, 30, 0);
            stamper.close();

            FileOutputStream outputStream = new FileOutputStream("test2.pdf");
            IOUtils.write(baos.toByteArray(), outputStream);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }


    }

    public Document writeDocument(String name) {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(name));

            doc.open();
            PdfPTable table = new PdfPTable(4);
            table.addCell("1.1");
            table.addCell("1.1");
            table.addCell("1.1");
            table.addCell("1.1");
            table.addCell("1.1");
            table.addCell("1.1");
            table.addCell("1.1");
            table.addCell("1.1");

            doc.add(table);
            doc.add(new Paragraph("bububu"));

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        doc.close();

        return doc;
    }

    public void invoice(String name, Order order) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(name));

            document.open();
            document.newPage();
            Font font = new Font();
            font.setSize(35);

            Paragraph first = new Paragraph("Faktura Nr");
            first.setAlignment(HorizontalAlignment.CENTER.getId());
            first.setFont(font);

            Table table = new Table(2);
            Cell sprzedawca = new Cell("Sprzedawca:" + System.lineSeparator() + "Podatnik:" + System.lineSeparator() + "NIP:" + System.lineSeparator()
                    + "Adres:");
            sprzedawca.setBorder(Rectangle.NO_BORDER);

            Cell nabywca = new Cell("Nabywca:" + System.lineSeparator() + "Imię i nazwisko lub nazwa:" + System.lineSeparator() + "NIP:" + System.lineSeparator()
                    + "Adres:");
            nabywca.setBorder(Rectangle.NO_BORDER);
            table.addCell(sprzedawca);
            table.addCell(nabywca);
            table.setBorder(Rectangle.NO_BORDER);
            table.setWidth(100);

            float[] columnSize = {30F, 180F, 150F, 45F, 45F};

            PdfPTable main = new PdfPTable(columnSize);
            main.setTotalWidth(document.getPageSize().getWidth() - 72);
            main.setHorizontalAlignment(0);
            main.setLockedWidth(true);

            Cell cell = new Cell();


            main.addCell("Lp.");
            main.addCell("Nazwa (rodzaj) towaru lub usługi (zakres wykonywanych usług)");
            main.addCell("Podstawa prawna zwolnienia od podatku");
            main.addCell("Miara");
            main.addCell("Ilość");
            int x = 0;
            for (AnxInOrder anx : order.getAnxInOrders()
            ) {
                main.addCell(String.valueOf(x));
                main.addCell(anx.getAnxieties().getName());
                main.addCell("Brak");
                main.addCell(anx.getAnxieties().getUnit());
                main.addCell(String.valueOf(anx.getAmount()));
                x++;
            }

            float[] size = {50, 50, 50, 50, 50, 50, 50, 50, 50};
            PdfPTable main2 = new PdfPTable(size);
            main2.setTotalWidth(document.getPageSize().getWidth() - 72);
            main2.setHorizontalAlignment(0);
            main2.setLockedWidth(true);


            main2.addCell(createCellSpan("Cena jednostkowa bez podatku", 2));
            main2.addCell(createCellSpan("Wartość towarów (usług) bez podatku", 2));
            main2.addCell(createCellHVA("Stawka podatku"));
            main2.addCell(createCellSpan("Kwota podatku", 2));
            main2.addCell(createCellSpan("Wartość towarów (usług) wraz z podatkiem", 2));
            main2.addCell(createCellHVA("zł"));
            main2.addCell(createCellHVA("gr"));
            main2.addCell(createCellHVA("zł"));
            main2.addCell(createCellHVA("gr"));
            main2.addCell(createCellHVA("%"));
            main2.addCell(createCellHVA("zł"));
            main2.addCell(createCellHVA("gr"));
            main2.addCell(createCellHVA("zł"));
            main2.addCell(createCellHVA("gr"));

            BigDecimal wartoscZpodatkiem;
            BigDecimal cenaJednostkowaZpodatkiem;
            BigDecimal cenaJednostkowaBezPodatku;
            BigDecimal wartoscBezPodatku;
            BigDecimal kwotaPodatku;

            //23,8,5,0,zw
            BigDecimal[] wartoscBezPodatkux = {BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0),
                    BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0)};
            BigDecimal[] kwotaPodatkux = {BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0),
                    BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0)};
            BigDecimal[] wartoscx = {BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0),
                    BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0)};
            String[] taxRate = {"23", "8", "5", "0", "zw"};


            for (AnxInOrder anx : order.getAnxInOrders()
            ) {
                wartoscZpodatkiem = new BigDecimal(anx.getAmount() * anx.getAnxieties().getPrice()).setScale(2, RoundingMode.HALF_DOWN);
                cenaJednostkowaBezPodatku = new BigDecimal(anx.getAnxieties().getPrice() - (anx.getAnxieties().getPrice() * anx.getAnxieties().getTaxRate() / 100))
                        .setScale(2, RoundingMode.HALF_DOWN);
                wartoscBezPodatku = cenaJednostkowaBezPodatku.multiply(BigDecimal.valueOf(anx.getAmount()));
                kwotaPodatku = wartoscZpodatkiem.subtract(wartoscBezPodatku).setScale(2, RoundingMode.HALF_DOWN);

                if (anx.getAnxieties().getTaxRate() == 23) {
                    wartoscBezPodatkux[0] = wartoscBezPodatkux[0].add(wartoscBezPodatku).setScale(2, RoundingMode.HALF_DOWN);
                    kwotaPodatkux[0] = kwotaPodatkux[0].add(kwotaPodatku).setScale(2, RoundingMode.HALF_DOWN);
                    wartoscx[0] = wartoscx[0].add(wartoscZpodatkiem).setScale(2, RoundingMode.HALF_DOWN);
                } else if (anx.getAnxieties().getTaxRate() == 8) {
                    wartoscBezPodatkux[1] = wartoscBezPodatkux[1].add(wartoscBezPodatku).setScale(2, RoundingMode.HALF_DOWN);
                    kwotaPodatkux[1] = kwotaPodatkux[1].add(kwotaPodatku).setScale(2, RoundingMode.HALF_DOWN);
                    wartoscx[1] = wartoscx[1].add(wartoscZpodatkiem).setScale(2, RoundingMode.HALF_DOWN);
                } else if (anx.getAnxieties().getTaxRate() == 5) {
                    wartoscBezPodatkux[2] = wartoscBezPodatkux[2].add(wartoscBezPodatku).setScale(2, RoundingMode.HALF_DOWN);
                    kwotaPodatkux[2] = kwotaPodatkux[2].add(kwotaPodatku).setScale(2, RoundingMode.HALF_DOWN);
                    wartoscx[2] = wartoscx[2].add(wartoscZpodatkiem).setScale(2, RoundingMode.HALF_DOWN);
                } else if (anx.getAnxieties().getTaxRate() == 0) {
                    wartoscx[3] = wartoscx[3].add(wartoscZpodatkiem).setScale(2, RoundingMode.HALF_DOWN);
                } else {
                    wartoscx[4] = wartoscx[4].add(wartoscZpodatkiem).setScale(2, RoundingMode.HALF_DOWN);
                }


                main2.addCell(createCellHVA(getFullAmount(cenaJednostkowaBezPodatku)));
                main2.addCell(createCellHVA(getDecimalPart(cenaJednostkowaBezPodatku)));
                main2.addCell(createCellHVA(getFullAmount(wartoscBezPodatku)));
                main2.addCell(createCellHVA(getDecimalPart(wartoscBezPodatku)));
                main2.addCell(createCellHVA(anx.getAnxieties().getTaxRate() + ""));
                main2.addCell(createCellHVA(getFullAmount(kwotaPodatku)));
                main2.addCell(createCellHVA(getDecimalPart(kwotaPodatku)));
                main2.addCell(createCellHVA(getFullAmount(wartoscZpodatkiem)));
                main2.addCell(createCellHVA(getDecimalPart(wartoscZpodatkiem)));
            }
            for (int y = 0; y < 5; y++) {
                wartoscBezPodatkux[5] = wartoscBezPodatkux[5].add(wartoscBezPodatkux[y]);
                kwotaPodatkux[5] = kwotaPodatkux[5].add(kwotaPodatkux[y]);
                wartoscx[5] = wartoscx[5].add(wartoscx[y]);
            }
            PdfPTable main3 = new PdfPTable(size);
            main3.setTotalWidth(document.getPageSize().getWidth() - 72);
            main3.setHorizontalAlignment(0);
            main3.setLockedWidth(true);
            for (int y = 0; y < 6; y++) {
                if (y < 5) {
                    main3.addCell(createCellSpan("", 2));
                    main3.addCell(createCellHVA(getFullAmount(wartoscBezPodatkux[y])));
                    main3.addCell(createCellHVA(getDecimalPart(wartoscBezPodatkux[y])));
                    main3.addCell(createCellHVA(taxRate[y]));
                    main3.addCell(createCellHVA(getFullAmount(kwotaPodatkux[y])));
                    main3.addCell(createCellHVA(getDecimalPart(kwotaPodatkux[y])));
                    main3.addCell(createCellHVA(getFullAmount(wartoscx[y])));
                    main3.addCell(createCellHVA(getDecimalPart(wartoscx[y])));
                } else {
                    main3.addCell(createCellSpan("Razem", 2));
                    main3.addCell(createCellHVA(getFullAmount(wartoscBezPodatkux[5])));
                    main3.addCell(createCellHVA(getDecimalPart(wartoscBezPodatkux[5])));
                    main3.addCell(createCellHVA(""));
                    main3.addCell(createCellHVA(getFullAmount(kwotaPodatkux[5])));
                    main3.addCell(createCellHVA(getDecimalPart(kwotaPodatkux[5])));
                    main3.addCell(createCellHVA(getFullAmount(wartoscx[5])));
                    main3.addCell(createCellHVA(getDecimalPart(wartoscx[5])));
                }
            }

            Paragraph sign = new Paragraph(System.lineSeparator()+System.lineSeparator()+System.lineSeparator()+"Podpis wystawcy");
            sign.setAlignment(HorizontalAlignment.CENTER.getId());


            document.add(first);
            document.add(table);
            document.add(new Paragraph(System.lineSeparator()));
            document.add(main);
            document.add(new Paragraph(System.lineSeparator()));
            document.add(main2);
            document.add(new Paragraph(System.lineSeparator()));
            document.add(main3);
            document.add(sign);


        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        document.close();

    }

    private PdfPCell createCellSpan(String content, int spanSize) {
        Cell cell = new Cell();
        cell.add(new Phrase(content));
        cell.setColspan(spanSize);
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);

        return cell.createPdfPCell();
    }

    private PdfPCell createCellHVA(String content) {
        Cell cell = new Cell();
        cell.add(new Phrase(content));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        return cell.createPdfPCell();
    }

    private String getFullAmount(BigDecimal bigDecimal) {
        return bigDecimal.toString().substring(0, bigDecimal.toString().indexOf("."));
    }

    private String getDecimalPart(BigDecimal bigDecimal) {
        return bigDecimal.toString().substring(bigDecimal.toString().indexOf(".") + 1);
    }

}
