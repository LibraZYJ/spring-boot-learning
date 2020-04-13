package com.soft1851.springboot.aop.annotation;

import java.lang.annotation.*;

/**
 * @author Yujie_Zhao
 * @ClassName 认证鉴权注解
 * @Description TODO
 * @Date 2020/4/13  15:32
 * @Version 1.0
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthToken {

    /**
     * 访问接口所需要的身份(角色)，默认为空,登录即可访问，可以定义多个
     * @return String[]
     */
    String[] role_name() default "";
}
