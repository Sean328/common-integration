package ironass.annotation;

import com.ironass.annotation.sample2.AspectService;
import ironass.BaseSpringTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author sean
 * @date 2019/03/03/23:12
 **/
public class Sample2Test extends BaseSpringTest {
    @Resource
    private AspectService aspectService;

    @Test
    public void testSample(){
        aspectService.getUser("lixin","25");
    }
}
