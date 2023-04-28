import com.zhou.demo.persist.po.CategoryTreeVo;
import com.zhou.demo.service.CategoryService;
import com.zhou.demo.utils.CategoryUtil;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName categorytest
 * @Author
 * @Date 2023/4/28 15:44
 * @Version
 * @Description
 */

public class categorytest {
    public static void main(String[] args) {
        test();
    }

    @Test
    public static  void test(){
        // 先获取所有的数据表数据
        CategoryService categoryService = new CategoryService();
        List<CategoryTreeVo> allMenuTreeVoList = categoryService.selectCategoryList();

        List<CategoryTreeVo> menuTreeVOTreeList = CategoryUtil.toTree(allMenuTreeVoList);
        for (CategoryTreeVo  vo :menuTreeVOTreeList) {
            System.out.println(vo.getName());
            if(ObjectUtils.isEmpty(vo.getParentId())){
                for(CategoryTreeVo child:vo.getChildMenu()){
                    System.out.println(child.getName());
                }
            }
        }

    }
}
