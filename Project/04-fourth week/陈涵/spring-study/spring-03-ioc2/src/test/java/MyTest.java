import com.ch.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
//        User user = new User();
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        User user = (User) context.getBean("User");
        user.show();
    }
}
