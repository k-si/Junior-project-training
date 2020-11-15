import java.io.IOException;
import java.security.GeneralSecurityException;

public class Demo {
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        new MailUtil("C:\\Users\\z\\Desktop\\1.docx").sendMessage();
    }
}
