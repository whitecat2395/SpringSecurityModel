package com.zhou.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Author zhouzheng
 * @Date 2023/3/7 12:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParams {

    /**
     * 模糊查询关键字
     */
    private String keyword;
    /**
     * 页范围
     */
    private Integer pagesize;
    /**
     * 页数
     */
    private Integer pagenum;
    /**
     * 页起始位置
     */
    private Integer pageindex;
     /**
     * 总页数
     */
    private Integer total;

}
