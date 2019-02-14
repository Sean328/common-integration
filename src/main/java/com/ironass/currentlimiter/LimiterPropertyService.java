package com.ironass.currentlimiter;

import com.ironass.currentlimiter.domain.LimiterProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author lixin
 * @date 2019-02-11 15:52
 **/
public class LimiterPropertyService {

    private static final Logger logger = LoggerFactory.getLogger(LimiterPropertyService.class);

    private Resource resource;


    public LimiterProperty getLimiterInfo(){

        Properties properties = new Properties();

        try {
            InputStream inputStream = resource.getInputStream();
            properties.load(inputStream);




        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
