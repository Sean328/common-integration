package com.ironass.annotation.sample1;

/**
 * @author sean
 * @date 2019/03/03/22:56
 **/
public class User {
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
