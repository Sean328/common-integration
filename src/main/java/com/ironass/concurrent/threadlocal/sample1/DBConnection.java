package com.ironass.concurrent.threadlocal.sample1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author lixin
 * @date 2019-03-04 17:29
 **/
public class DBConnection {

    private static DBConnection instance = null;

    private static String driverClassName = null;

    private static String connectionUrl = null;

    private static String userName = null;

    private static String password = null;

    private static Connection conn = null;

    private static Properties jdbcProp = null;

    private DBConnection() {

    }

    private static Properties getConfigFromPropertiesFile() throws Exception {

        Properties prop = null;
        prop = JdbcProperties.getPropObjFromFile();
        return prop;

    }

    private static void initJdbcParameters(Properties prop) {

        driverClassName = prop.getProperty(Constants.DRIVER_CLASS_NAME);
        connectionUrl = prop.getProperty(Constants.CONNECTION_URL);
        userName = prop.getProperty(Constants.DB_USER_NAME);
        password = prop.getProperty(Constants.DB_USER_PASSWORD);

    }

    private static void createConnection() throws Exception {

        Class.forName(driverClassName);
        conn = DriverManager.getConnection(connectionUrl, userName, password);

    }

    public Connection getConnection() throws Exception {
        return conn;
    }

    public synchronized static DBConnection getInstance() throws Exception {

        if (instance == null) {
            jdbcProp = getConfigFromPropertiesFile();
            instance = new DBConnection();
        }

        initJdbcParameters(jdbcProp);

        createConnection();

        return instance;

    }

}
