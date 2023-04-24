package com.zhou.demo.persist.po;

import lombok.Data;

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
}
