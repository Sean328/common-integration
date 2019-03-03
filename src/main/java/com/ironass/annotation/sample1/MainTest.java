package com.ironass.annotation.sample1;
/**
 * @author sean
 * @date 2019/03/03/22:57
 **/
public class MainTest {
    public static void main(String[] args) {
        User user = UserFactory.create();

        System.out.println(user.getName());
        System.out.println(user.getAge());
    }
}
