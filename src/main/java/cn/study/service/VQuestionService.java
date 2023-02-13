package cn.study.service;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VMenu;
import cn.study.entity.VQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @description v_question
 * @author zhangcc
 * @date 2023-02-09
 */
public interface VQuestionService extends IService<VQuestion> {


    Boolean add(VQuestionDto vQuestionDto);

}
