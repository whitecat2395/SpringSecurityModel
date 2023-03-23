package com.zhou.demo.persist.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName StatuePo
 * @Author
 * @Date 2023/3/17 16:14
 * @Version
 * @Description
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatuePo {
        private Integer id;
        private String status;
        private Integer userId;
}
