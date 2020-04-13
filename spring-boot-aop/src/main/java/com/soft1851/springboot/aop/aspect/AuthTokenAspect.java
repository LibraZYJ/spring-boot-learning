package com.soft1851.springboot.aop.aspect;

import com.soft1851.springboot.aop.annotation.AuthToken;
import com.soft1851.springboot.aop.common.Result;
import com.soft1851.springboot.aop.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yujie_Zhao
 * @ClassName AuthTokenAspect
 * @Description TODO
 * @Date 2020/4/13  15:40
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class AuthTokenAspect {
    /**
     * 配置加上自定义注解方法为切点
     *
     * @param authToken
     */
    @Pointcut("@annotation(authToken)")
    public void doAuthToken(AuthToken authToken) {

    }

    @Around(value = "doAuthToken(authToken)", argNames = "pjp,authToken")
    public Object doAround(ProceedingJoinPoint pjp, AuthToken authToken) throws Throwable {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        //取得注解中的 role_name
        String[] roleNames = authToken.role_name();
        //没有role的值
        if (roleNames.length <= 1) {
            //只需要认证（登录）
            String id = request.getHeader("id");
            //如果id为空，可以调用目标方法
            if (id != null) {
                return pjp.proceed();
            }
            return Result.failure(ResultCode.USER_CODE_TIMEOUT);
        }else{
            //验证身份
            String role = request.getHeader("role");
            //便利roleNames数组，匹配role
            for (String roleName : roleNames) {
                if (roleName.equals(role)){
                    //匹配成功,可调用目标方法
                    return pjp.proceed();
                }
            }
            return Result.failure(ResultCode.USER_NO_AUTH);
        }

        //取得请求头中的值


    }
}
