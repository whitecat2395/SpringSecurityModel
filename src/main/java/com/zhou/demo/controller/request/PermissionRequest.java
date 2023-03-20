package com.zhou.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName PermissionRequest
 * @Author
 * @Date 2023/3/17 16:12
 * @Version
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest {
    private Integer id;
    private String permissionName;
    private String permissionKey;
    private String status;
    private Boolean delFlag;
}
