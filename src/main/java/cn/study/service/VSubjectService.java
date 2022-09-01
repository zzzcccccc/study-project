package cn.study.service;

import cn.study.entity.VGrade;
import cn.study.entity.VSubject;

import java.util.List;

public interface VSubjectService {

    List<VGrade> getAllGrade();

    List<VSubject> getAllSubject();
}
