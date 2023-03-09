package cn.study.service.impl;

import cn.study.constant.CommonConstants;
import cn.study.dto.VQuestionDto;
import cn.study.entity.VAnswer;
import cn.study.entity.VPaper;
import cn.study.entity.VPaperQues;
import cn.study.entity.VQuestion;
import cn.study.mapper.*;
import cn.study.service.VPaperService;
import cn.study.vo.VPaperVo;
import cn.study.vo.VUserSubClaVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class VPaperServiceImpl extends ServiceImpl<VPaperMapper, VPaper> implements VPaperService {

    @Resource
    VPaperMapper vPaperMapper;

    @Resource
    VQuestionMapper vQuestionMapper;

    @Resource
    VPaperQuesMapper vPaperQuesMapper;

    @Resource
    VAnswerMapper vAnswerMapper;

    @Resource
    VUserSubjectClassMapper vUserSubjectClassMapper;

    @Override
    public IPage getPageAdmin(Page page, VQuestionDto vQuestionDto) {

        IPage iPage = vPaperMapper.getPageAdmin(page, vQuestionDto);
        List<VPaperVo> examPapers = iPage.getRecords();
        for (VPaperVo examPaperVo : examPapers) {
            Long paperId = examPaperVo.getId();
            List<VQuestion> listByExamId = vQuestionMapper.getListByPaperId(paperId);
            examPaperVo.setQuestions(listByExamId);
        }
        return iPage;
    }

    @Override
    public IPage getPage(Page page, VQuestionDto vQuestionDto) {
        IPage iPage = vPaperMapper.getPage(page, vQuestionDto);
        List<VPaperVo> examPapers = iPage.getRecords();
        for (VPaperVo examPaperVo : examPapers) {
            Long paperId = examPaperVo.getId();
            List<VQuestion> listByExamId = vQuestionMapper.getListByPaperId(paperId);
            examPaperVo.setQuestions(listByExamId);
        }
        return iPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(VQuestionDto vQuestionDto) {

        Integer gradeId = vQuestionDto.getGradeId();
        Integer subjectId = vQuestionDto.getSubjectId();

        VPaper vPaper = new VPaper();
        BeanUtils.copyProperties(vQuestionDto, vPaper);
        Integer[] classIds = vQuestionDto.getClassIds();
        if (classIds != null) {
            vPaper.setClassIdArray(Arrays.toString(classIds));
        }
        int insert = vPaperMapper.insert(vPaper);
        if (insert == 1) {
            Long paperId = vPaper.getId();
            String[] quesIds = vQuestionDto.getQuesIds().split(",");
            for (int i = 0; i < quesIds.length; i++) {
                VPaperQues vPaperQues = new VPaperQues();
                vPaperQues.setPaperId(paperId);
                vPaperQues.setQuesId(Long.parseLong(quesIds[i]));
                vPaperQuesMapper.insert(vPaperQues);
            }
            // 创编试卷 班级一旦提交不可修改
            if (classIds.length > 0) {  // 指定班级（卷子一次作废）
                for (int i = 0; i < classIds.length; i++) {
                    Long teacherId = vUserSubjectClassMapper.selectTeacher(gradeId, subjectId, classIds[i]);
                    List<VUserSubClaVo> userSubClaVos = vUserSubjectClassMapper.selectUserByClassId(null, classIds[i]);
                    for (VUserSubClaVo userSubClaVo : userSubClaVos) {
                        VAnswer vAnswer = new VAnswer();
                        vAnswer.setClassId(classIds[i]);
                        vAnswer.setUserId(userSubClaVo.getUserId());
                        vAnswer.setRealName(userSubClaVo.getRealName());
                        vAnswer.setTeacherId(teacherId);
                        vAnswer.setPaperId(paperId);
                        vAnswerMapper.insert(vAnswer);
                    }
                }
            } else { //具体到年级
                List<VUserSubClaVo> userSubClaVos = vUserSubjectClassMapper.selectUserByClassId(gradeId, null);
                for (VUserSubClaVo userSubClaVo : userSubClaVos) {
                    VAnswer vAnswer = new VAnswer();

                    String classIds1 = userSubClaVo.getClassIds();
                    String[] strings = classIds1.substring(1, classIds1.length() - 1).split(",");
                    Integer[] integers = new Integer[1];
                    integers[0] = Integer.parseInt(strings[0]);
                    vAnswer.setClassId(integers[0]);

                    vAnswer.setUserId(userSubClaVo.getUserId());
                    vAnswer.setRealName(userSubClaVo.getRealName());
                    vAnswer.setPaperId(paperId);
                    vAnswerMapper.insert(vAnswer);
                }
            }

        } else {
            return 0;
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(VQuestionDto vQuestionDto) {
        VPaper vPaper = new VPaper();
        BeanUtils.copyProperties(vQuestionDto, vPaper);
        Integer[] classIds = vQuestionDto.getClassIds();
        vPaper.setClassIdArray(Arrays.toString(classIds));
        int insert = vPaperMapper.insert(vPaper);
        if (insert == 1) {
            Long id = vPaper.getId();
            String[] quesIds = vQuestionDto.getQuesIds().split(",");
            for (int i = 0; i < quesIds.length; i++) {
                VPaperQues vPaperQues = new VPaperQues();
                vPaperQues.setPaperId(id);
                vPaperQues.setQuesId(Long.parseLong(quesIds[i]));
                vPaperQuesMapper.insert(vPaperQues);
            }
        } else {
            return 0;
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int del(Long paperId) {
        VPaper vPaper = this.baseMapper.selectById(paperId);
        vPaper.setDelFlag(CommonConstants.STATUS_DEL);
        int i = this.baseMapper.updateById(vPaper);
        if (i > 0) {
            vPaperQuesMapper.delete(Wrappers.<VPaperQues>lambdaQuery()
                    .eq(VPaperQues::getPaperId, paperId));

            vAnswerMapper.delete(Wrappers.<VAnswer>lambdaQuery()
                    .eq(VAnswer::getPaperId, paperId)
                    .isNotNull(VAnswer::getAnswerArray));
        }
        return 0;
    }
}
