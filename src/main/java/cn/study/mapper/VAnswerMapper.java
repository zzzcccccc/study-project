package cn.study.mapper;

import cn.study.dto.VAnswerInfoDto;
import cn.study.entity.VAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VAnswerMapper extends BaseMapper<VAnswer> {

    IPage getPageToAnswerInfo(Page page , @Param("query") VAnswerInfoDto VAnswerInfoDto);
}
