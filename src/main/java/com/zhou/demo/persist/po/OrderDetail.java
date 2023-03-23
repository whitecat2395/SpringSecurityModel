package com.zhou.demo.persist.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName orderDetail
 * @Author
 * @Date 2023/3/22 16:30
 * @Version
 * @Description
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private Integer id;
    private Integer orderId;
    private Integer goodsId;
    private Integer sellerId;
    private Integer expressId;
    private Integer expressName;
    private Integer goodsImg;
    private Integer buyCount;
    private Integer goodsPrice;
    private Integer goodsName;
}
