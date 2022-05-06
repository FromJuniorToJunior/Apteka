package A1.Apteka.Apteka.Services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

@Service
public class PdfService {

    public void appendDocument() {
        try (PdfReader reader = new PdfReader("test.pdf")) {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader,baos);

            PdfPTable table = new PdfPTable(2);
            table.addCell("asd");
            table.addCell("asd");
            table.addCell("asd");
            table.addCell("asd");

            stamper.addAnnotation(PdfAnnotation.createText(stamper.getWriter(),new Rectangle(30f, 750f, 80f, 800f),"test",
                    "Testowy Content",true,null),1);
            stamper.getUnderContent(1);

            stamper.close();

            FileOutputStream outputStream = new FileOutputStream("test2.pdf");
            IOUtils.write(baos.toByteArray(),outputStream);
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


}
