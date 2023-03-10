package com.zhou.demo.persist.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName MenuStatuePo
 * @Author
 * @Date 2023/3/10 14:42
 * @Version
 * @Description
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuStatuePo {
    private Integer id;
    private String status;
}
