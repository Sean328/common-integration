package com.ironass.annotation.sample1;

import java.lang.reflect.Method;

/**
 * @author sean
 * @date 2019/03/03/22:57
 **/
public class UserFactory {
    public static User create()
    {
        User user = new User();

        // 获取User类中所有的方法（getDeclaredMethods也行）
        Method[] methods = User.class.getMethods();

        try
        {
            for (Method method : methods)
            {
                // 如果此方法有注解，就把注解里面的数据赋值到user对象
                if (method.isAnnotationPresent(Init.class))
                {
                    Init init = method.getAnnotation(Init.class);
                    method.invoke(user, init.value());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return user;
    }
}
