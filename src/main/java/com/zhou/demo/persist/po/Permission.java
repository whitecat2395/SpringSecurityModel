package com.zhou.demo.persist.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName Permission
 * @Author
 * @Date 2023/3/17 16:13
 * @Version
 * @Description
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    private Integer id;
    private String permissionName;
    private String permissionKey;
    private String status;
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
