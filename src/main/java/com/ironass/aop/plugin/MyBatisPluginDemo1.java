package com.ironass.aop.plugin;

import org.apache.commons.lang3.ClassUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 自定一个MyBaties的拦截器插件，用于xxxx功能的实现
 *
 * @author lixin
 * @date 2019-06-12 11:11
 **/

/**
 * MyBatis 允许你在已映射语句执行过程中的某一点通过自定义插件进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：
 * <p>
 * Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)          --执行sql时拦截
 * ParameterHandler (getParameterObject, setParameters)                                                  --获取、设置参数
 * ResultSetHandler (handleResultSets, handleOutputParameters)                                           --处理结果集
 * StatementHandler (prepare, parameterize, batch, update, query)                                        --记录sql
 */
@Intercepts(value = {@Signature(type = ParameterHandler.class, method = "setParameters", args = {MappedStatement.class, Object.class})})
public class MyBatisPluginDemo1 implements Interceptor {

    private static final String PARAM_KEY = "tenantId";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("插件拦截成功打印日志");

        if (invocation.getTarget() instanceof ParameterHandler) {
            return invokeParameter(invocation);
        }
        return invocation.proceed();
    }

    private Object invokeParameter(Invocation invocation) throws Exception {

        ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
        PreparedStatement statement = (PreparedStatement) invocation.getArgs()[0];

        //反射获取boundSql对象，此对象中包含生成的sql语句和sql的参数map映射
        Field boundSqlField = parameterHandler.getClass().getDeclaredField("boundSql");
        boundSqlField.setAccessible(true);
        BoundSql boundSql = (BoundSql) boundSqlField.get(parameterHandler);

        List<String> paramNames = new ArrayList<>();
        //如果参数映射的map中不含需要的key则直接返回
        boolean hashKey = hasParamKey(paramNames, boundSql.getParameterMappings());
        //若参数中不包含想要的值则直接返回
        if (!hashKey) {
            return invocation.proceed();
        }

        //反射获取目标对象
        Field paramField = parameterHandler.getClass().getDeclaredField("parameterObject");
        paramField.setAccessible(true);
        Object po = paramField.get(parameterHandler);

        //改写参数
        po = processSingle(po, paramNames);

        //改写后的参数设置到原来的parameterHandler对象当中
        paramField.set(parameterHandler, po);
        parameterHandler.setParameters(statement);

        return null;
    }

    private Object processSingle(Object obj, List<String> paramNames) throws Exception {
        //使用mybatis中自带工具构建方法map
        Map<String, Object> paraMap = new MapperMethod.ParamMap<>();
        if (obj == null) {
            paraMap.put(PARAM_KEY, 1L);
            obj = paraMap;
        } else if (ClassUtils.isPrimitiveOrWrapper(obj.getClass())
                || String.class.isAssignableFrom(obj.getClass())
                || Number.class.isAssignableFrom(obj.getClass())) {
            if (paramNames.size() == 1) {
                paraMap.put(paramNames.iterator().next(), obj);
                paraMap.put(PARAM_KEY, 1L);
                obj = paraMap;
            }
        } else {
            processParam(obj);
        }

        return obj;
    }

    private void processParam(Object obj) throws InvocationTargetException, IllegalAccessException {
        //处理参数对象
        if(obj instanceof Map){
            ((Map) obj).putIfAbsent(PARAM_KEY,1L);
        } else {
            PropertyDescriptor ps = BeanUtils.getPropertyDescriptor(obj.getClass(),PARAM_KEY);
            if(ps != null &&  ps.getReadMethod()!=null && ps.getWriteMethod()!=null ){
                Object value = ps.getReadMethod().invoke(obj);
                if(value == null){
                    ps.getWriteMethod().invoke(obj,1L);
                }
            }
        }

    }

    private boolean hasParamKey(List<String> paramNames, List<ParameterMapping> parameterMappings) {
        boolean hasKey = false;
        for (ParameterMapping mapping : parameterMappings) {
            if (PARAM_KEY.equals(mapping.getProperty())) {
                hasKey = true;
            } else {
                paramNames.add(mapping.getProperty());
            }
        }
        return hasKey;
    }


    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
