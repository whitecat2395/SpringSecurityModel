package com.zhou.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName MenuRequest
 * @Author
 * @Date 2023/3/10 14:37
 * @Version
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {
    private Integer id;
    private String menuName;
    private String icon;
    private String url;
    private String status;
    private String perms;
    private Integer pid;
    private String remark;
    private String level;
    private Integer isLink;
    private Boolean delFlag;
}
