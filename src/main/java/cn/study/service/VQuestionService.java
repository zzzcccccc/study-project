package cn.study.service;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VMenu;
import cn.study.entity.VQuestion;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @description v_question
 * @author zhangcc
 * @date 2023-02-09
 */
public interface VQuestionService extends IService<VQuestion> {


    Boolean add(VQuestionDto vQuestionDto);

    IPage getPage(Page page, VQuestionDto vQuestionDto);

    Boolean edit(VQuestion vQuestion);

    Integer del(Long id);
}
