package com.zhou.demo.persist.po;

import com.alibaba.druid.filter.AutoLoad;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName Menus
 * @Author
 * @Date 2023/3/10 14:38
 * @Version
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
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
    private Integer createBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Integer updateBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private Boolean delFlag;
}
