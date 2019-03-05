package com.ironass.concurrent.threadlocal.sample1.dao;

import com.xxl.integration.concurrent.threadlocal.sample1.ConnectionManager;
import com.xxl.integration.concurrent.threadlocal.sample1.domain.Student;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 * @author lixin
 * @date 2019-03-04 17:55
 **/
public class StudentDAOImpl implements StudentDAO {
    private Log logger = LogFactory.getLog(this.getClass());


    @Override
    public void addStudent(Student std) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement(StudentDAOSql.ADD_STUDENT);
            pstmt.setString(1, std.getsNo());
            pstmt.setString(2, std.getsName());
            pstmt.setString(3, std.getsAge());
            pstmt.setString(4, std.getGender());
            pstmt.setDate(5, Date.valueOf(std.getSbirth()));
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("addStudent:" + e.getMessage(), e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
            } catch (Exception e) {

            }
        }
    }


    public void delStudent(String sNo) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionManager.getConnection();
//            pstmt = conn.prepareStatement(StudentDAOSql.DEL_STUDENT);
            pstmt.setString(1, sNo);
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new Exception("delStudent:" + e.getMessage(), e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
            } catch (Exception e) {

            }

        }

    }
}
