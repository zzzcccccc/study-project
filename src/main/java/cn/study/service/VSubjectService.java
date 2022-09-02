package cn.study.service;

import cn.study.entity.VGrade;
import cn.study.entity.VSubject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface VSubjectService extends IService<VSubject> {

    List<VGrade> getAllGrade();

    List<VSubject> getAllSubject();

}
