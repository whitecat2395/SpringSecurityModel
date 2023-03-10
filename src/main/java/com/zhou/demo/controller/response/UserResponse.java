package com.zhou.demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class UserResponse implements Serializable{
        private Integer id;
        private String userName;
        private String statue;
        /**
         * 角色id
         *
         */
        private Integer roleId;
        /**
         * 用户类型 普通用户|管理员用户
         */
        private String userType;
        private String realName;
        private String email;
        private String phone;
        /**
         * 头像
         */
        private String avatar;
        /**
         * 地址
         */
        private String address;
        private String sex;
        private Date createTime;
        private Boolean delFlag;
}
