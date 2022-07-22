package cn.study.service;

import cn.study.entity.VPermission;
import cn.study.vo.VPermissionVo;
import io.swagger.models.auth.In;

import java.util.List;

public interface VPermissionService {


    List<VPermissionVo> getAllPermission();

    VPermission getPermissionById(Integer id);

    String addPermission(VPermission vPermission);

    List<VPermission> getPermissionOne();

    String editPermission(VPermission vPermission);

    String delPermission(Integer id,Integer parentId);
}
