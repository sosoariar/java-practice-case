package com.soso.plugin;

import com.soso.Tools;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({
        @Signature(type= StatementHandler.class,
                method = "prepare",
                args = {Connection.class,Integer.class})
})
public class MyPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Tools.log.info("对方法进行了增强....");
        return invocation.proceed();
    }
    /*为什么需要这个方法*/
    @Override
    public Object plugin(Object target) {
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }
    /*为什么需要这个方法*/
    @Override
    public void setProperties(Properties properties) {
        Tools.log.info("获取到的配置文件的参数是："+properties);
    }
}
