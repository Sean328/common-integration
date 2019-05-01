package com.ironass.concurrent.threadlocal.sample1.dao;

import com.ironass.concurrent.threadlocal.sample1.domain.Student;

public interface StudentDAO {
    public void addStudent(Student std) throws Exception;
}
