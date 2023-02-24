package cn.study.service;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VExamPaper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface VExamPaperService extends IService<VExamPaper> {


    IPage getPage(Page page, VQuestionDto vQuestionDto);
}
