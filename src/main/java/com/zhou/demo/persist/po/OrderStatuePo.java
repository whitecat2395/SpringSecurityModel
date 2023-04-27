package com.zhou.demo.persist.po;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName OrderStatuePo
 * @Author
 * @Date 2023/4/24 14:25
 * @Version
 * @Description
 */
@Data
public class OrderStatuePo {
    private String id;
    private String status;
    private Integer userId;
    private Date updateTime;
}
