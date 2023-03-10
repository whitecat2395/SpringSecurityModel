package com.zhou.demo.persist.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Goods
 * @Author
 * @Date 2023/3/9 15:21
 * @Version
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {
    /**
     * 商品ID
     */
    @Id
    private Integer id;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 卖家ID
     */
    private Integer userId;
    /**
     * 关键字
     */
    private String uukey;
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
     * 热度
     */
    private Integer heat;
    /**
     * 图片1
     */
    private String picture;
    /**
     * 售出0 还有1
     */
    private String  sellStatus;

    /**
     * 上架时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    private Boolean delFlag;
    /**
     * 分类
     */
//    private Category category;
}
