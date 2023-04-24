package com.zhou.demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName TableData
 * @Author zz
 * @Date 2023/4/24 10:07
 * @Version
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableData implements Serializable {
    private String id;
    private String title;
    private String detail;
    private String Price;
    private List<String> urls;
}
