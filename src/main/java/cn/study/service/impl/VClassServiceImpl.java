package cn.study.service.impl;

import cn.study.dto.VClassDto;
import cn.study.entity.VClass;
import cn.study.mapper.VClassMapper;
import cn.study.service.VClassService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VClassServiceImpl extends ServiceImpl<VClassMapper, VClass> implements VClassService {

    @Override
    public List<VClass> getAll() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public IPage getPage(Page page, VClassDto vClassDto) {
        IPage pageSubject = this.baseMapper.getPage(page, vClassDto);
        return pageSubject;
    }

}
