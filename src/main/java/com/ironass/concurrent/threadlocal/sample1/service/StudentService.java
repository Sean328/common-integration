package com.ironass.concurrent.threadlocal.sample1.service;

import com.xxl.integration.concurrent.threadlocal.sample1.domain.Student;

public interface StudentService {
    public void addStudent(Student std) throws Exception;
}
