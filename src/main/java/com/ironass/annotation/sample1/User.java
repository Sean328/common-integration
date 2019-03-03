package com.ironass.annotation.sample1;

import com.ironass.base.BaseDomain;

/**
 * @author sean
 * @date 2019/03/03/22:56
 **/
public class User extends BaseDomain {
    private String name;
    private String age;

    public String getName()
    {
        return name;
    }

    @Init(value = "lixin")
    public void setName(String name)
    {
        this.name = name;
    }

    public String getAge()
    {
        return age;
    }

    @Init(value = "23")
    public void setAge(String age)
    {
        this.age = age;
    }
}
