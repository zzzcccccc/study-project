package cn.study.service.impl;

import cn.study.entity.VLink;
import cn.study.mapper.StudyMapper;
import cn.study.service.StudyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudyServiceImpl extends ServiceImpl<StudyMapper, VLink> implements StudyService {
}
