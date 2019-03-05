package com.ironass.concurrent.threadlocal.sample1.service;

import com.xxl.integration.concurrent.threadlocal.sample1.ConnectionManager;
import com.xxl.integration.concurrent.threadlocal.sample1.dao.ClassRoomDAO;
import com.xxl.integration.concurrent.threadlocal.sample1.dao.ClassRoomDAOImpl;
import com.xxl.integration.concurrent.threadlocal.sample1.dao.StudentDAO;
import com.xxl.integration.concurrent.threadlocal.sample1.dao.StudentDAOImpl;
import com.xxl.integration.concurrent.threadlocal.sample1.domain.Student;

/**
 * @author lixin
 * @date 2019-03-04 17:51
 **/
public class StudentServiceImpl implements StudentService {

    @Override
    public void addStudent(Student std) throws Exception {

        StudentDAO studentDAO = new StudentDAOImpl();
        ClassRoomDAO classRoomDAO = new ClassRoomDAOImpl();

        try {
            ConnectionManager.BeginTrans(true);
            studentDAO.addStudent(std);
            classRoomDAO.addStudentClassRoom(std.getClassRoomId(), std.getsNo());
            ConnectionManager.commit();

        } catch (Exception e) {
            try {
                ConnectionManager.rollback();
            } catch (Exception de) {
            }
            throw new Exception(e);
        } finally {
            try {
                ConnectionManager.close();
            } catch (Exception e) {
            }
        }
    }
}
