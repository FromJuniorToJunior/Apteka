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
import java.util.Arrays;
import java.util.Properties;


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


