package cn.study.service.impl;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VExamPaper;
import cn.study.entity.VQuestion;
import cn.study.mapper.VQuestionMapper;
import cn.study.service.VQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VQuestionServiceImpl extends ServiceImpl<VQuestionMapper, VQuestion>  implements VQuestionService {


    @Override
    public Boolean add(VQuestionDto vQuestionDto) {
        VExamPaper vExamPaper = new VExamPaper();
        BeanUtils.copyProperties(vQuestionDto,vExamPaper);

        List<VQuestion> questionList = vQuestionDto.getQuestions();
        for (VQuestion question: questionList ) {
            VQuestion vQuestion = new VQuestion();
            BeanUtils.copyProperties(question,vQuestion);
            this.baseMapper.insert(vQuestion);

        }
        return null;
    }
}
