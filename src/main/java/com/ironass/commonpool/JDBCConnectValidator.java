package com.ironass.commonpool;

import com.ironass.commonpool.Pool.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 校验器，校验数据库连接是否失效
 * @author lixin
 * @date 2019-01-28 11:44BoundedPool
 * PoolFactory
 **/
public class JDBCConnectValidator implements Validator<Connection> {
    private static final Logger logger = LoggerFactory.getLogger(JDBCConnectValidator.class);

    @Override
    public Boolean isValid(Connection connection) {
        if(connection == null){
            return false;
        }
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            logger.warn("获取关闭状态异常,连接关闭：{}",e);
            try {
                connection.close();
            } catch (SQLException e1) {
                try {
                    logger.warn("连接关闭，连接信息：{}",connection.getClientInfo(),e);
                } catch (SQLException e2) {
                }
            }
            return Boolean.FALSE;
        }
    }

    @Override
    public void invalidate(Connection connection) {

        try {
            connection.close();
        } catch (SQLException e) {
            try {
                logger.error("connection 关闭异常，connection: {}",connection.getClientInfo(),e);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }
}
