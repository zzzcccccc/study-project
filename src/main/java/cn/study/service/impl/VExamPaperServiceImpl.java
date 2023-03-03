package cn.study.service.impl;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VExamPaper;
import cn.study.entity.VExamQuest;
import cn.study.mapper.VExamPaperMapper;
import cn.study.mapper.VExamQuestMapper;
import cn.study.service.VExamPaperService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VExamPaperServiceImpl extends ServiceImpl<VExamPaperMapper, VExamPaper> implements  VExamPaperService {

    @Autowired
    VExamPaperMapper vExamPaperMapper;

    @Autowired
    VExamQuestMapper vExamQuestMapper;

    @Override
    public IPage getPage(Page page, VQuestionDto vQuestionDto) {
        IPage iPage = vExamPaperMapper.selectPage(page, null);
        return iPage;
    }

    @Override
    public int add(VQuestionDto vQuestionDto) {
        VExamPaper vExamPaper = new VExamPaper();
        BeanUtils.copyProperties(vQuestionDto,vExamPaper);
        int insert = vExamPaperMapper.insert(vExamPaper);
        if (insert==1){
            Long id = vExamPaper.getId();
            String[] quesIds = vQuestionDto.getQuesIds().split(",");
            for (int i = 0; i < quesIds.length; i++) {
                VExamQuest vExamQuest = new VExamQuest();
                vExamQuest.setExamId(id);
                vExamQuest.setQuestId(Long.parseLong(quesIds[i]));
                vExamQuestMapper.insert(vExamQuest);
            }
        }else{
            return 0;
        }
        return 1;
    }
}
