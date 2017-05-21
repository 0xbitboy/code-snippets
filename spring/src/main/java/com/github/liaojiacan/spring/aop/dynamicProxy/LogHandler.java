package com.github.liaojiacan.spring.aop.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liaojiacan on 2017/5/21.
 */
public class LogHandler implements InvocationHandler{

    private static final ThreadLocal<SimpleDateFormat> DF = new ThreadLocal<>();

    private Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(DF.get()==null){
            DF.set(new SimpleDateFormat("yyyy-M-dd HH:mm:ss"));
        }
        System.out.println("LOG:EXECUTE AT "+DF.get().format(new Date()));

       Object result =  method.invoke(target,args);

        System.out.println("LOG:EXECUTE FINISH AT "+DF.get().format(new Date()));
        return result;
    }
}
