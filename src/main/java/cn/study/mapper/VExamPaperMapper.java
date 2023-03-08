package cn.study.mapper;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VExamPaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VExamPaperMapper extends BaseMapper<VExamPaper> {

    IPage getPage(Page page,@Param("query") VQuestionDto vQuestionDto);
}
