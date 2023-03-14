package cn.study.service.impl;

import cn.study.dto.VAnswerInfoDto;
import cn.study.dto.VAnswerElsDto;
import cn.study.dto.VAnswerQuesDto;
import cn.study.dto.VQuestionDto;
import cn.study.entity.VAnswer;
import cn.study.entity.VQuestion;
import cn.study.mapper.VAnswerMapper;
import cn.study.mapper.VQuestionMapper;
import cn.study.service.VAnswerService;
import cn.study.utils.CompareUtil;
import cn.study.vo.VAnwserVo;
import cn.study.vo.VPaperVo;
import cn.study.vo.VQuestionVo;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class VAnswerServiceImpl extends ServiceImpl<VAnswerMapper, VAnswer> implements VAnswerService {

    @Resource
    private VQuestionMapper vQuestionMapper;


    @Override
    public int insert(VQuestionDto vQuestionDto) {
//        this.baseMapper.insert()
        return 0;
    }

    @Override
    public int edit(VAnswerElsDto vAnswerElsDto) {
        Long answerId = vAnswerElsDto.getAnswerId();
        VAnswer vAnswer = new VAnswer();

        List<VAnswerQuesDto> questions = vAnswerElsDto.getQuestions();
        vAnswer.setAnswerArray(JSON.toJSONString(questions));
        vAnswer.setAnswerState("1");
        vAnswer.setId(answerId);
        int flag = this.baseMapper.updateById(vAnswer);
        return 0;
    }

    @Override
    public IPage getPageToAnswerInfo(Page page, VAnswerInfoDto VAnswerInfoDto) {
        return this.baseMapper.getPageToAnswerInfo(page, VAnswerInfoDto);
    }

    @Override
    public VAnswer getList(VAnswerInfoDto vAnswerInfoDto) {
        VAnswer vAnswer = this.baseMapper.selectOne(Wrappers.<VAnswer>lambdaQuery()
                .eq(VAnswer::getPaperId, vAnswerInfoDto.getPaperId())
                .eq(VAnswer::getUserId, vAnswerInfoDto.getUserId()));
        String correctState = vAnswer.getCorrectState();

        if (correctState.equals("1")){
            return vAnswer;
        }

        String quesTypeName = "";
        String answerArray = vAnswer.getAnswerArray();
        List<VQuestionVo> vQuestions = JSON.parseArray(answerArray, VQuestionVo.class);
        for (int i = 0; i < vQuestions.size(); i++) {
            VQuestionVo vQuestion = vQuestions.get(i);
            Integer quesTypeId = vQuestion.getQuesTypeId();
            String answer = vQuestion.getAnswer();
            String userAnswer = vQuestion.getUserAnswer();
            String[] answers = CompareUtil.toStringArray(answer);
            String[] userAnswers = CompareUtil.toStringArray(userAnswer);
            // 题目分数
            Integer score = vQuestion.getScore();
            vQuestion.setUserScore(0);
            if (quesTypeId == 1 || quesTypeId == 5) {
                if (userAnswers.length > 0 && userAnswers[0].equals(answers[0])) {
                    vQuestion.setUserScore(score);
                }
            } else if (quesTypeId == 2) {
                if (userAnswers.length > 0) {
                    boolean equal = CompareUtil.isEqual(userAnswers, answers);
                    if (equal) {
                        vQuestion.setUserScore(score);
                    }
                }

            }
            // 题型名称
            if (quesTypeId < 3) {
                if (quesTypeId == 1) {
                    quesTypeName = "单选题";
                }else{
                    quesTypeName = "多选题";
                }
            } else if (quesTypeId < 5) {
                if (quesTypeId == 3) {
                    quesTypeName = "填空题";
                } else {
                    quesTypeName = "简答题";
                }
            } else {
                quesTypeName = "判断题";
            }
            vQuestion.setQuesTypeName(quesTypeName);
        }

        String s = JSON.toJSONString(vQuestions);
        vAnswer.setAnswerArray(s);
        return vAnswer;
    }

    @Override
    public int correct(VAnswerInfoDto vAnswerInfoDto) {
        VAnswer vAnswer = this.baseMapper.selectOne(Wrappers.<VAnswer>lambdaQuery()
                .eq(VAnswer::getPaperId, vAnswerInfoDto.getPaperId())
                .eq(VAnswer::getUserId, vAnswerInfoDto.getUserId()));
        vAnswer.setGrade(vAnswerInfoDto.getGrade());

        List<VQuestionVo> scoreList = vAnswerInfoDto.getScoreList();
        String s = JSON.toJSONString(scoreList);
        vAnswer.setAnswerArray(s);
        vAnswer.setCorrectState("1");
        this.baseMapper.updateById(vAnswer);
        return 0;
    }
}
