package com.zhou.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest  implements Serializable {
    /**
     * 前端接口User对象
     */
    private Integer id;

    private String userName;

    private String passWord;

    private String realName;

    private String sex;

    private String email;

    private String phone;
    /**
     * 地址
     */
    private String address;
}
