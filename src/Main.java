
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {

    final static String username = "test2@gmail.com";
    final static String password = "";
    static Session session;
    public static void main(String[] args) {

        sendMail("test1@gmail.com");


    }

    public static Session getSession() {
        if (session == null) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");


            session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        }
        return session;
    }

    public static void sendMail(String receiverAddress) {
        try {
            Message message = new MimeMessage(Main.getSession());
            message.setFrom(new InternetAddress("no-reply.graphflow@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(receiverAddress));
            message.setSubject("Testing Subject");
            message.setText("Dear User,"+System.lineSeparator() + "your system is about to go " +
                "kaboom");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
