package com.yjh.controller;


import com.yjh.pojo.SysLog;
import com.yjh.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime;
    private Class executionClass;
    private Method executionMethod;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    //前置通知
    @Before("execution(* com.yjh.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        //获取访问的类
        executionClass = jp.getTarget().getClass();
        //获取访问的方法的名称
        String methodName=jp.getSignature().getName();
        //获取访问的方法的参数

        Object[] args=jp.getArgs();
        if(args==null||args.length==0){
            //获得无参数的方法
            executionMethod = executionClass.getMethod(methodName);
        }else{
            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i]=args[i].getClass();
            }
            executionMethod=executionClass.getMethod(methodName,classArgs);
        }

    }

    //后置通知
    // 主要获取日志中其它信息，时长、ip、url...
    @After("execution(* com.yjh.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        long executionTime=new Date().getTime()-visitTime.getTime();
        //获取操作的url,通过java反射的方式获取
        //获取类上的requestmapping对象
        if(executionClass!=SysLogController.class){
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                //获取方法上的requestmapping对象
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String url="";
                    url=classAnnotation.value()[0]+methodAnnotation.value()[0];
                    //获取请求的ip地址
                    String ip = request.getRemoteAddr();
                    //获取当前的操作用户对象
                    SecurityContext context = SecurityContextHolder.getContext();
                    User principal = (User) context.getAuthentication().getPrincipal();
                    //获取当前的操作用户名
                    String username = principal.getUsername();


                    SysLog sysLog=new SysLog();
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]"+executionClass.getName()+" "+"[方法名]"+executionMethod.getName());
                    sysLog.setVisitTime(visitTime);
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
