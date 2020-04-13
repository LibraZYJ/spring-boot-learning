package com.soft1851.springboot.aop.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author Yujie_Zhao
 * @ClassName User
 * @Description TODO
 * @Date 2020/4/7  13:31
 * @Version 1.0
 **/
@Data
@Builder
public class User {
    private String userId;
    private String emailFlag;
    private String phoneNumber;
    private String phoneNumberFlag;
    private String fullName;
    private String studentNo;
    private String avatarUrl;
    private java.sql.Timestamp joinTime;
    private String clazzCourseId;
    private long score;
    private String roles;
    private long ranking;
}
