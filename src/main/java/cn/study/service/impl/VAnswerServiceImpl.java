package cn.study.service.impl;

import cn.study.dto.VQuestionDto;
import cn.study.entity.VAnswer;
import cn.study.mapper.VAnswerMapper;
import cn.study.service.VAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class VAnswerServiceImpl extends ServiceImpl<VAnswerMapper, VAnswer> implements VAnswerService {


    @Override
    public int insert(VQuestionDto vQuestionDto) {
//        this.baseMapper.insert()
        return 0;
    }
}
