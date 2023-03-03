package cn.study.service.impl;

import cn.study.constant.CommonConstants;
import cn.study.dto.VQuestionDto;
import cn.study.entity.VExamPaper;
import cn.study.entity.VExamQuest;
import cn.study.entity.VQuestion;
import cn.study.mapper.VExamPaperMapper;
import cn.study.mapper.VExamQuestMapper;
import cn.study.mapper.VQuestionMapper;
import cn.study.service.VQuestionService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.*;

@Service
public class VQuestionServiceImpl extends ServiceImpl<VQuestionMapper, VQuestion>  implements VQuestionService {

    @Resource
    VExamPaperMapper vExamPaperMapper;
    @Resource
    VExamQuestMapper vExamQuestMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(VQuestionDto vQuestionDto) {
        String check = vQuestionDto.getCheck();
        Integer gradeId = vQuestionDto.getGradeId();
        Integer subjectId = vQuestionDto.getSubjectId();
        List<Long> questIds = new ArrayList<>();
        try {
            List<VQuestion> questionList  = vQuestionDto.getQuestions();
            for (VQuestion question: questionList ) {
                System.out.println(question);
                question.setGradeId(gradeId);
                question.setSubjectId(subjectId);
                int insert = this.baseMapper.insert(question);
                if (insert!=1){
                    throw new Exception("保存失败1");
                }
                questIds.add(question.getId());
            }
            //check 1添加试卷 2只存到题库
            if ("1".equals(check)){
                VExamPaper vExamPaper = new VExamPaper();
                BeanUtils.copyProperties(vQuestionDto,vExamPaper);
                Integer[] classIds = vQuestionDto.getClassIds();
                vExamPaper.setClassIdArray(Arrays.toString(classIds));
                int insert2 = vExamPaperMapper.insert(vExamPaper);
                if (insert2!=1){
                    throw new Exception("保存失败2");
                }

                for (int i = 0; i < questIds.size(); i++) {
                    VExamQuest vExamQuest = new VExamQuest();
                    vExamQuest.setExamId(vExamPaper.getId());
                    vExamQuest.setQuestId(questIds.get(i));
                    int insert3 = vExamQuestMapper.insert(vExamQuest);
                    if (insert3!=1){
                        throw new Exception("保存失败3");
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            //springboot+事务，多张表的操作事务回滚
            //@Transactional和try catch捕获异常会让注解失效
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Boolean.TRUE;
    }


    @Override
    public Boolean edit(VQuestion vQuestion) {
        this.baseMapper.updateById(vQuestion);
        return Boolean.TRUE;
    }

    @Override
    public Integer del(Long id) {
        VQuestion vQuestion = this.baseMapper.selectById(id);
        vQuestion.setDelFlag(CommonConstants.STATUS_DEL);
        int i = this.baseMapper.updateById(vQuestion);
        return i;
    }

    @Override
    public IPage getPage(Page page, VQuestionDto vQuestionDto) {
        return this.baseMapper.getPage(page, vQuestionDto);
    }

}
