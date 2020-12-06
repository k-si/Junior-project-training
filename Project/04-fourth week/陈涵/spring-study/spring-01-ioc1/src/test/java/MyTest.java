import com.ch.Dao.UserDaoMysqlImpl;
import com.ch.Service.UserService;
import com.ch.Service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
//        UserService userService = new UserServiceImpl();
//
//        userService.setUserDao(new UserDaoMysqlImpl());
//        userService.getUser();

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceImpl serviceImpl = (UserServiceImpl) context.getBean("serviceImpl");
        serviceImpl.getUser();
    }
}
