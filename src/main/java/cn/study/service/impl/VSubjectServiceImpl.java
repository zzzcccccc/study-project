package cn.study.service.impl;

import cn.study.dto.VSubjectDto;
import cn.study.entity.VGrade;
import cn.study.entity.VSubject;
import cn.study.mapper.VGradeMapper;
import cn.study.mapper.VSubjectMapper;
import cn.study.service.VSubjectService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<VSubject> vSubjects = this.baseMapper.selectList(null);

        List<VSubject> newList = vSubjects.stream().collect(Collectors
                .collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(VSubject::getName))),
                        ArrayList::new));

        return newList;
    }

    @Override
    public IPage getPageSubject(Page page, VSubjectDto subjectDto) {
        IPage pageSubject = this.baseMapper.getPageSubject(page, subjectDto);
        return pageSubject;
    }

}
