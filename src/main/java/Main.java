import javax.mail.MessagingException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PagesController ctrl = new PagesController();
        StubPagesFiller.fill(ctrl);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String passwd = scanner.nextLine();
        System.out.println("Enter target email:");
        String to = scanner.nextLine();
        try {
            EmailSendingUtil.send(
                    username,
                    passwd,
                    to,
                    "Softaria task",
                    LetterFormattingUtil.getLetter(ctrl)
            );
        } catch (MessagingException ex) {
            System.out.println("Sorry, something went wrong");
            return;
        }
        System.out.println("Message sent successfully");
    }
}
