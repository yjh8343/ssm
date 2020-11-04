import com.yjh.dao.UserDao;
import com.yjh.pojo.UserInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao =ac.getBean(UserDao.class);
        UserInfo userInfo = userDao.findByUserId("");
        System.out.println(userInfo.getUsername());
    }
}
