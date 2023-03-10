package com.zhou.demo.persist.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName GoodsStatuePo
 * @Author
 * @Date 2023/3/9 15:14
 * @Version
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsStatuePo {
    private Integer id;
    private String statue;
    private String uuKey;
    private Integer userId;

}
