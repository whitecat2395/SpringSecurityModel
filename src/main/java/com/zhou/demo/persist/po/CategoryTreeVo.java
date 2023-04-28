package com.zhou.demo.persist.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @ClassName category
 * @Author
 * @Date 2023/4/27 17:11
 * @Version
 * @Description
 */

@Data
@TableName("t_menu")
public class CategoryTreeVo {
    @TableId("id")
    private Integer id;

    /**
     * 菜单的英文名称
     */
    @TableField("name")
    private String name;
    /**
     * 父菜单 id
     */
    @TableField("parentId")
    private int parentId;

    /**
     * 菜单可见性
     */
    @TableField("status")
    private Boolean status;

    /**
     * 该菜单的所有子菜单，注明非数据库中的字段
     */
    @TableField(exist = false)
    private List<CategoryTreeVo> childMenu;

}
