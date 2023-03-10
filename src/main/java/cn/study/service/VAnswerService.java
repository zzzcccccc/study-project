package cn.study.service;

import cn.study.dto.VAnswerDto;
import cn.study.dto.VQuestionDto;
import cn.study.entity.VAnswer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface VAnswerService extends IService<VAnswer> {

    int insert(VQuestionDto vQuestionDto);

    int edit(List<VAnswerDto> vAnswerDto);
}
