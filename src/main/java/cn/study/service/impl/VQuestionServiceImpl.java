package cn.study.service.impl;

import cn.study.entity.VMenu;
import cn.study.entity.VQuestion;
import cn.study.mapper.VMenuMapper;
import cn.study.mapper.VQuestionMapper;
import cn.study.service.VQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class VQuestionServiceImpl extends ServiceImpl<VQuestionMapper, VQuestion>  implements VQuestionService {
}
