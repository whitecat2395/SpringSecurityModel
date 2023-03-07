package com.zhou.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserResponse
 * @Author zz
 * @Date 2023/3/7 14:52
 * @Version 1.0
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
        private Integer id;
        private String userName;
        private String statue;
        private Integer roleId;
        private String userType;
        private String realName;
        private String email;
        private String phone;
        private String avatar;
        private String address;
        private String sex;
        private Date createTime;
        private Boolean delFlag;
}
