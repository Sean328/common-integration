package ironass;

import com.ironass.aop.demo.UserService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author sean
 * @date 2019/02/16/23:57
 **/
public class AopTest extends BaseSpringTest{

    @Resource
    private UserService userService;

    @Test
    public void testAspectJ(){

        try {
            String s = userService.getDemoData();
            System.out.println(s);

        }catch (Exception e){
            System.out.println("测试异常时，aop中after-throwing是否生效");
        }

    }

}
