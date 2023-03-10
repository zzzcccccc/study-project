package cn.study.service.impl;

import cn.study.dto.VAnswerDto;
import cn.study.dto.VQuestionDto;
import cn.study.entity.VAnswer;
import cn.study.mapper.VAnswerMapper;
import cn.study.service.VAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VAnswerServiceImpl extends ServiceImpl<VAnswerMapper, VAnswer> implements VAnswerService {


    @Override
    public int insert(VQuestionDto vQuestionDto) {
//        this.baseMapper.insert()
        return 0;
    }

    @Override
    public int edit(List<VAnswerDto> vAnswerDto) {
//        this.baseMapper.updateById()
        return 0;
    }
}
