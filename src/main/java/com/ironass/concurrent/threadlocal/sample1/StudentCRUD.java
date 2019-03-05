package com.ironass.concurrent.threadlocal.sample1;

import com.xxl.integration.concurrent.threadlocal.sample1.domain.Student;
import com.xxl.integration.concurrent.threadlocal.sample1.service.StudentService;
import com.xxl.integration.concurrent.threadlocal.sample1.service.StudentServiceImpl;

/**
 * @author lixin
 * @date 2019-03-04 18:01
 **/
public class StudentCRUD {

    public void addStudent() throws Exception {
        StudentService stdService = new StudentServiceImpl();
        Student std = new Student();

        std.setsNo("101");
        std.setsName("abc");
        std.setSbirth("1977/01/01");
        std.setsAge("35");
        std.setGender("m");
        std.setClassRoomId("1");
        std.setClassRoomName("class1");
        stdService.addStudent(std);
    }


    public static void main(String[] args) {

        StudentCRUD testStudentCRUD = new StudentCRUD();

        try {
            testStudentCRUD.addStudent();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
