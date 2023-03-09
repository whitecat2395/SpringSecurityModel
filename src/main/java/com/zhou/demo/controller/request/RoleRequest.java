package com.zhou.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName RoleRequest
 * @Author
 * @Date 2023/3/9 10:46
 * @Version
 * @Description
 */

@Data
public class RoleRequest implements Serializable {
    private Integer id;
    private String roleName;
    private String remark;
}
