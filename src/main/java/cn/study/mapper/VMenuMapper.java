package cn.study.mapper;

import cn.study.entity.VMenu;
import cn.study.vo.VMenuVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VMenuMapper extends BaseMapper<VMenu> {


    List<VMenuVo> getList(@Param("parentId") Integer parentId,@Param("showFlag") Integer showFlag);


    List<VMenuVo> getChildMenu(@Param("menuId") Integer menuId,
                             @Param("roleIds") String roleIds);

    List<VMenuVo> getMenuOne(String roleIds);

    List<Integer> findMenuIdsByRoleId(Integer roleId);

}
