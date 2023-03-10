package com.zhou.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @ClassName GoodsRequest
 * @Author
 * @Date 2023/3/9 15:25
 * @Version
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsRequest {

    private Integer id;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 价格
     */
    private Double price;

    /**
     * 原价格
     */
    private Double originalPrice;
    /**
     * 描述
     */
    private String goodsDetail;
    /**
     * 数量
     */
    private Integer goodsNumber;

    /**
     * 图片1
     */
    private String picture;
}
