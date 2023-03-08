package cn.study.service.impl;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VExamPaper;
import cn.study.entity.VExamQuest;
import cn.study.entity.VQuestion;
import cn.study.mapper.VExamPaperMapper;
import cn.study.mapper.VExamQuestMapper;
import cn.study.mapper.VQuestionMapper;
import cn.study.service.VExamPaperService;
import cn.study.vo.VExamPaperVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class VExamPaperServiceImpl extends ServiceImpl<VExamPaperMapper, VExamPaper> implements  VExamPaperService {

    @Resource
    VExamPaperMapper vExamPaperMapper;

    @Resource
    VQuestionMapper vQuestionMapper;

    @Resource
    VExamQuestMapper vExamQuestMapper;

    @Override
    public IPage getPage(Page page, VQuestionDto vQuestionDto) {

        IPage iPage = vExamPaperMapper.getPage(page, vQuestionDto);
        List<VExamPaperVo> examPapers  = iPage.getRecords();
        for (VExamPaperVo examPaperVo:examPapers) {
            Long examId = examPaperVo.getId();
            List<VQuestion> listByExamId = vQuestionMapper.getListByExamId(examId);
            examPaperVo.setQuestions(listByExamId);
        }
        return iPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(VQuestionDto vQuestionDto) {
        VExamPaper vExamPaper = new VExamPaper();
        BeanUtils.copyProperties(vQuestionDto,vExamPaper);
        Integer[] classIds = vQuestionDto.getClassIds();
        vExamPaper.setClassIdArray(Arrays.toString(classIds));
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
