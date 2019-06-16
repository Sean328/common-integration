package com.ironass.aop.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * 注意有用到注解
 * @author lixin
 * @date 2019-06-13 15:37
 **/
@Intercepts({@Signature(type = Executor.class,method = "query",args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(args = {MappedStatement.class, Object.class}, method = "update", type = Executor.class)})
public class MybatisUpdateTimePlugin implements Interceptor {

    private static final Logger logger = LogManager.getLogger(MybatisUpdateTimePlugin.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        //获取sql命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        logger.info("获取到的sql命令为：{}",sqlCommandType);

        //获取参数
        Object paramter = invocation.getArgs()[1];
        if(paramter != null){
            Field[] decleardFields = paramter.getClass().getDeclaredFields();
            for (Field field : decleardFields){
                if(field.getAnnotation(UpdateTime.class)!= null){
                    if(SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)){
                        field.setAccessible(true);
                        if (field.get(paramter) == null) {
                            field.set(paramter, new Date());
                        }
                    }
                }
            }


        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        logger.info("执行到了 plugin 方法");
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        logger.info("执行到了 setProperties 方法");
    }
}
