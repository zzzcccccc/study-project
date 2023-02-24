package cn.study.service.impl;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VExamPaper;
import cn.study.mapper.VExamPaperMapper;
import cn.study.service.VExamPaperService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VExamPaperServiceImpl extends ServiceImpl<VExamPaperMapper, VExamPaper> implements  VExamPaperService {

    @Autowired
    VExamPaperMapper vExamPaperMapper;

    @Override
    public IPage getPage(Page page, VQuestionDto vQuestionDto) {
        IPage iPage = vExamPaperMapper.selectPage(page, null);
        return iPage;
    }
}
