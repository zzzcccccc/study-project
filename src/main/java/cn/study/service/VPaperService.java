package cn.study.service;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VPaper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface VPaperService extends IService<VPaper> {


    IPage getPageAdmin(Page page, VQuestionDto vQuestionDto);

    int add(VQuestionDto vQuestionDto);

    int insert(VQuestionDto vQuestionDto);

    int del(Long paperId);

    IPage getPage(Page page, VQuestionDto vQuestionDto);
}
