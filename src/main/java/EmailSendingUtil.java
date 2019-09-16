import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public interface EmailSendingUtil {
    static void send(String username, String password, String to, String subject, String text)
    throws MessagingException {
        send(username, password, to, subject, text, "smtp.gmail.com", "587");
    }

    static void send(
            String username,
            String password,
            String to,
            String subject,
            String text,
            String smtpHost,
            String smtpPort
    ) throws MessagingException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            //message.setText(text, "UTF-8");
            message.setContent(text, "text/plain; charset=UTF-8");
            Transport transport = session.getTransport();
            transport.connect(username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AuthenticationFailedException ex){
            System.err.println("Your email or password was incorrect");
            throw ex;
        } catch (MessagingException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
