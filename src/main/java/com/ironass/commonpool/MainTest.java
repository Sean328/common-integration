package com.ironass.commonpool;

import java.sql.Connection;

/**
 * @author lixin
 * @date 2019-01-28 14:15
 **/
public class MainTest {

    public static void main(String[] args) {
        Pool<Connection> pool = new BoundedBlockingPool<Connection>(10,new JDBCConnectValidator(),
                new JDBCConnectFactory("","","",""));

        Connection conn = pool.get();

    }
}
