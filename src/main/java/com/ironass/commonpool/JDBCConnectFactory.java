package com.ironass.commonpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC 连接工厂，用来创建新的数据库连接
 * @author lixin
 * @date 2019-01-28 14:03
 **/
public class JDBCConnectFactory implements ObjectFactory{

    private String url;
    private String userName;
    private String password;

    public JDBCConnectFactory(String driver,String url,String userName,String password){
        super();
        this.url = url;
        this.userName = userName;
        this.password = password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Could not find driver in classpath",e);
        }
    }


    @Override
    public Connection createNew() {
        try {
            return DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable create connection,url",e);
        }
    }
}
