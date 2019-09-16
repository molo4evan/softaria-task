import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LetterFormattingUtil {
    public static String getLetter(PagesController ctrl){
        StringBuilder out = new StringBuilder();
        out.append("Здравствуйте, дорогая и.о. секретаря\n");
        out.append("За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n");
        out.append("\n\t1. Исчезли следующие страницы:\n");
        appendURLs(out, ctrl.getDeletedURLs());
        out.append("\t2. Появились следующие новые страницы:\n");
        appendURLs(out, ctrl.getAddedURLs());
        out.append("\t3. Изменились следующие страницы:\n");
        appendURLs(out, ctrl.getEditedURLs());
        out.append("\nС уважением,\nавтоматизированная система\nмониторинга.");
        return new String(out.toString().getBytes(), StandardCharsets.UTF_8);
    }

    private static void appendURLs(StringBuilder builder, List<String> urls){
        builder.append("\t{\n");
        for (String url : urls) {
            builder.append("\t\t").append(url).append("\n");
        }
        builder.append("\t}\n");
    }
}
