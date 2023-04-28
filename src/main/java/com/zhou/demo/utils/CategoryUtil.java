package com.zhou.demo.utils;

import com.zhou.demo.persist.po.CategoryTreeVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CategoryUtil
 * @Author
 * @Date 2023/4/27 17:14
 * @Version
 * @Description
 */

public class CategoryUtil {
    /**
     * 所有的菜单
     */
    private static List<CategoryTreeVo> allList = null;

    /**
     * 转换为树形结构
     *
     * @param list 所有的节点
     * @return 树结构菜单
     */
    public static List<CategoryTreeVo> toTree(List<CategoryTreeVo> list) {
        allList = new ArrayList<>(list);

        // 获取所有的一级菜单，父菜单 id 为 0
        List<CategoryTreeVo> roots = new ArrayList<>();

        // 遍历
        for (CategoryTreeVo categoryTreeVo : list) {
            if (categoryTreeVo.getParentId() == 0) {
                roots.add(categoryTreeVo);
            }
        }

        // 删除一级菜单
        allList.removeAll(roots);

        // 对每一个一级菜单添加二级菜单
        for (CategoryTreeVo categoryTreeVo : roots) {
            // 设置子菜单
            categoryTreeVo.setChildMenu(getCurrentChildrenMenu(categoryTreeVo));
        }
        return roots;
    }

    /**
     * 通过父菜单获取子菜单列表
     *
     * @param parentMenu 父菜单
     * @return 子菜单列表
     */
    private static List<CategoryTreeVo> getCurrentChildrenMenu(CategoryTreeVo parentMenu) {
        // 判断当前节点是否已经存在子结点
        List<CategoryTreeVo> childMenuList;
        if (parentMenu.getChildMenu() == null) {
            childMenuList = new ArrayList<>();
            ;
        } else {
            childMenuList = parentMenu.getChildMenu();
        }

        // 遍历所有的菜单，除了一级菜单，之前删过
        for (CategoryTreeVo childMenu : allList) {
            if (parentMenu.getId() == childMenu.getParentId()) {
                // 某个菜单的父菜单 id 等于当前菜单，这个菜单就是子菜单
                childMenuList.add(childMenu);
            }
        }

        allList.removeAll(childMenuList);

        return childMenuList;
    }
}
