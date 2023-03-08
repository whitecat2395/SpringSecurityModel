package com.zhou.demo.persist.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserStatuePo
 * @Author zz
 * @Date 2023/3/8 16:10
 * @Version 1.0
 * @Description 更新用户状态Po
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatuePo {
    private Integer id;
    private String statue;
}
