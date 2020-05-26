import com.ihrm.system.SystemApplication;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: hyl
 * @date: 2020/05/26
 **/
@SpringBootTest(classes = SystemApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class IhrmSystemTest {

    @Test
    public void testMd5(){
        String password = new Md5Hash("123456" , "13800000002" , 3).toString();
        System.out.println(password.equals("c8b7722b1139bb9b346409e503302e82"));

    }
}
