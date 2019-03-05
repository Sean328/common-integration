package com.ironass.concurrent.threadlocal.sample1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * @author lixin
 * @date 2019-03-04 17:36
 **/
public class JdbcProperties {

    private static Log logger = LogFactory.getLog(JdbcProperties.class);

    public static Properties getPropObjFromFile() {

        Properties objProp = new Properties();
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();

        URL url = classLoader.getResource(Constants.JDBC_PROPERTIES_FILE);

        if (url == null) {
            classLoader = ClassLoader.getSystemClassLoader();
            url = classLoader.getResource(Constants.JDBC_PROPERTIES_FILE);
        }

        File file = new File(url.getFile());
        InputStream inStream = null;

        try {

            inStream = new FileInputStream(file);
            objProp.load(inStream);

        } catch (FileNotFoundException e) {
            objProp = null;
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                    inStream = null;
                }
            } catch (Exception e) {
            }
        }
        return objProp;
    }

}
