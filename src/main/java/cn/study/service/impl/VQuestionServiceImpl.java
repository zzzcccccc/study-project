package cn.study.service.impl;

import cn.study.constant.CommonConstants;
import cn.study.dto.VQuestionDto;
import cn.study.entity.VAnswer;
import cn.study.entity.VPaper;
import cn.study.entity.VPaperQues;
import cn.study.entity.VQuestion;
import cn.study.mapper.*;
import cn.study.service.VQuestionService;
import cn.study.vo.VUserSubClaVo;
import com.alibaba.fastjson.JSON;
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
    VPaperMapper vPaperMapper;
    @Resource
    VPaperQuesMapper vPaperQuesMapper;


    @Resource
    VAnswerMapper vAnswerMapper;

    @Resource
    VUserSubjectClassMapper vUserSubjectClassMapper;

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
                VPaper vPaper = new VPaper();
                BeanUtils.copyProperties(vQuestionDto, vPaper);
                Integer[] classIds = vQuestionDto.getClassIds();
                vPaper.setClassIdArray(Arrays.toString(classIds));
                int insert2 = vPaperMapper.insert(vPaper);
                if (insert2!=1){
                    throw new Exception("保存失败2");
                }
                Long paperId = vPaper.getId();
                for (int i = 0; i < questIds.size(); i++) {
                    VPaperQues vPaperQues = new VPaperQues();
                    vPaperQues.setPaperId(paperId);
                    vPaperQues.setQuesId(questIds.get(i));
                    int insert3 = vPaperQuesMapper.insert(vPaperQues);
                    if (insert3!=1){
                        throw new Exception("保存失败3");
                    }
                }

                // 创编试卷 班级一旦提交不可修改
                if (classIds.length>0){  // 指定班级（卷子一次作废）
                    for (int i = 0; i <classIds.length ; i++) {
                        Long teacherId = vUserSubjectClassMapper.selectTeacher(gradeId,subjectId,classIds[i]);
                        List<VUserSubClaVo> userSubClaVos = vUserSubjectClassMapper.selectUserByClassId(null,classIds[i]);
                        for (VUserSubClaVo userSubClaVo:userSubClaVos ) {
                            VAnswer vAnswer = new VAnswer();
                            vAnswer.setClassId(classIds[i]);
                            vAnswer.setUserId(userSubClaVo.getUserId());
                            vAnswer.setRealName(userSubClaVo.getRealName());
                            vAnswer.setTeacherId(teacherId);
                            vAnswer.setPaperId(paperId);
                            vAnswerMapper.insert(vAnswer);
                        }
                    }
                }else{ //具体到年级
                    List<VUserSubClaVo> userSubClaVos = vUserSubjectClassMapper.selectUserByClassId(gradeId,null);
                    for (VUserSubClaVo userSubClaVo:userSubClaVos ) {
                        VAnswer vAnswer = new VAnswer();

                        String classIds1 = userSubClaVo.getClassIds();
                        String[] strings = classIds1.substring(1,classIds1.length()-1).split(",");
                        Integer[] integers = new Integer[1];
                        integers[0] = Integer.parseInt(strings[0]);
                        vAnswer.setClassId(integers[0]);

                        vAnswer.setUserId(userSubClaVo.getUserId());
                        vAnswer.setRealName(userSubClaVo.getRealName());
                        vAnswer.setPaperId(paperId);
                        vAnswerMapper.insert(vAnswer);
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
