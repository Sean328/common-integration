package com.ironass.concurrent.threadlocal.sample1.dao;

import com.xxl.integration.concurrent.threadlocal.sample1.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author lixin
 * @date 2019-03-04 17:53
 **/
public class ClassRoomDAOImpl implements ClassRoomDAO {

    @Override
    public void addStudentClassRoom(String roomId, String sNo) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement(ClassRoomDAOSql.ADD_STUDENT_CLASSROOM);
            pstmt.setString(1, roomId);
            pstmt.setString(2, sNo);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("addStudentClassRoom:" + e.getMessage(), e);
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
