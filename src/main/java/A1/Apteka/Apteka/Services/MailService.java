package A1.Apteka.Apteka.Services;

import A1.Apteka.Apteka.Model.User;

import com.sun.mail.imap.IMAPSSLStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;


@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;


    public void sendMail(User user) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        FileSystemResource file = new FileSystemResource(new File("./text.xlsx"));
        FileSystemResource img = new FileSystemResource(new File("./paracetamol.jpg"));
        try {

            helper.setFrom("Apteka@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Test Message");
            helper.setText("<head><body>Test: <img src='cid:id123'></head></body>", true);

            helper.addInline("id123", img);


            helper.addAttachment(file.getFilename(), file);

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }


        javaMailSender.send(msg);


    }

    public void sendOfficialEmail(String emailTo, String subject, String content, String name, String workplace, String email, String phone, String department) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            FileSystemResource img1 = new FileSystemResource(new File("./cplus1.png"));
            FileSystemResource img2 = new FileSystemResource(new File("./f.jpg"));
            FileSystemResource img3 = new FileSystemResource(new File("./in.jpg"));
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailTo);
            helper.setSubject(subject);


            //read stopka file
            String stopka = content + System.lineSeparator();
            Scanner scanner = new Scanner(new File("./stopka.txt"));
            while (scanner.hasNextLine()) {
                stopka += scanner.nextLine();
            }
            stopka = stopka.replace("[[name]]", name);
            stopka = stopka.replace("[[workplace]]", workplace);
            stopka = stopka.replace("[[phone]]", phone);
            stopka = stopka.replace("[[email]]", email);
            stopka = stopka.replace("[[email2]]", email);
            stopka = stopka.replace("[[department]]", department);
            scanner.close();
            helper.setText(stopka, true);
            helper.addInline("img1", img1);
            helper.addInline("img2", img2);
            helper.addInline("img3", img3);

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        javaMailSender.send(message);


    }

    public void readMail() {
        try {

            //create properties field
            Properties properties = new Properties();

            properties.put("mail.imap.host", "imap.gmail.com");
            properties.put("mail.imap.port", "997");
            properties.put("mail.imap.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the IMAP store object and connect with the imap server
            Store store = emailSession.getStore("imaps");


            store.connect("imap.gmail.com", "alaskalicencjat@gmail.com", "arbuz486");

            //create the folder object and open it
            Folder emailFolder = store.getFolder("Inbox");

            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);


            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


