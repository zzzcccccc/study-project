package cn.study.mapper;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VQuestionMapper extends BaseMapper<VQuestion> {

    IPage getPage(Page page, @Param("query") VQuestionDto vQuestionDto);

    List<VQuestion> getListByPaperId(Long paperId);
}
