package cn.study.service;

import cn.study.dto.VClassDto;
import cn.study.dto.VSubjectDto;
import cn.study.entity.VClass;
import cn.study.entity.VGrade;
import cn.study.entity.VSubject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface VClassService extends IService<VClass> {


    List<VClass> getAll();

    IPage getPage(Page page, VClassDto vClassDto);
}
