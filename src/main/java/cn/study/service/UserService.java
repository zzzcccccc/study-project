package cn.study.service;

import cn.study.dto.VUserDto;
import cn.study.entity.VUser;
import cn.study.vo.VUserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface UserService {

    Integer doLogin(String userName, String password);

    IPage getUserPage(Page page, VUserDto vUserDto);

    Integer addUser(VUserDto vUserDto);

    VUser getOneByUserName(String userName);

    VUser getInfoById(Integer userId);

    Integer editUser(VUser vUser);

    Integer del(Integer userId);

    VUser getInfoByUsername(String username);
}
