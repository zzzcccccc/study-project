package cn.study.mapper;

import cn.study.dto.VUserDto;
import cn.study.entity.VLink;
import cn.study.entity.VUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<VUser> {

    IPage getPage(Page page,@Param("query") VUserDto vUserDto);
}
