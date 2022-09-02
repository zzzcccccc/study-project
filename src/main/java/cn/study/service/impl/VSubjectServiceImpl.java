package cn.study.service.impl;

import cn.study.constant.CommonConstants;
import cn.study.entity.VGrade;
import cn.study.entity.VLink;
import cn.study.entity.VSubject;
import cn.study.mapper.StudyMapper;
import cn.study.mapper.VGradeMapper;
import cn.study.mapper.VSubjectMapper;
import cn.study.service.StudyService;
import cn.study.service.VSubjectService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VSubjectServiceImpl extends ServiceImpl<VSubjectMapper, VSubject> implements VSubjectService {

    @Resource
    VGradeMapper vGradeMapper;

    @Override
    public List<VGrade> getAllGrade() {
        return vGradeMapper.selectList(null);
    }

    @Override
    public List<VSubject> getAllSubject() {
        return this.baseMapper.selectList(null);
    }

}
