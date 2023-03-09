package cn.study.service;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VAnswer;
import com.baomidou.mybatisplus.extension.service.IService;


public interface VAnswerService extends IService<VAnswer> {

    int insert(VQuestionDto vQuestionDto);
}
