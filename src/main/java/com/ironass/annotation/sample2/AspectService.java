package com.ironass.annotation.sample2;

import com.ironass.annotation.sample1.User;
import org.springframework.stereotype.Service;

/**
 * @author sean
 * @date 2019/03/03/23:08
 **/
@Service
public class AspectService {

    @LogAnno//这是我们自定义的注解，加上这个注解后就能够切到这个方法了。
    public User getUser(String  name, String age) {
        System.out.println("方法中。。。。。");
        User user=new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }
}
