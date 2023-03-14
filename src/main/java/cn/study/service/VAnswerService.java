package cn.study.service;

import cn.study.dto.VAnswerInfoDto;
import cn.study.dto.VAnswerElsDto;
import cn.study.dto.VQuestionDto;
import cn.study.entity.VAnswer;
import cn.study.vo.VAnwserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface VAnswerService extends IService<VAnswer> {

    int insert(VQuestionDto vQuestionDto);

    int edit(VAnswerElsDto vAnswerElsDto);

    IPage getPageToAnswerInfo(Page page, VAnswerInfoDto VAnswerInfoDto);

    VAnswer getList(VAnswerInfoDto vAnswerInfoDto);

    int correct(VAnswerInfoDto vAnswerInfoDto);
}
