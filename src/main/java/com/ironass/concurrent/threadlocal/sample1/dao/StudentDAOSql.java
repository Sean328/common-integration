package com.ironass.concurrent.threadlocal.sample1.dao;

/**
 * @author lixin
 * @date 2019-03-04 17:50
 **/
public class StudentDAOSql {

    public final static String ADD_STUDENT = "insert into t_student(sno, sname, sage, gender,sbirth)values(?,?,?,?,?)";
}
