package cn.study.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VUserDto;
import cn.study.entity.VUser;
import cn.study.mapper.UserMapper;
import cn.study.service.UserService;
import cn.study.service.impl.StpInterfaceImpl;
import cn.study.vo.VUserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Resource
    UserService userService;

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @GetMapping("doLogin")
    public RES doLogin(String userName, String password) {
        Integer flag = userService.doLogin(userName, password);
        if (flag==0){
            return RES.ok(CommonConstants.SUCCESS,"登录成功",null);
        }else if (flag==1){
            return RES.no(CommonConstants.FAIL,"用户名或密码错误");
        }
        return RES.no(CommonConstants.FAIL,"用户不存在");
    }

    /**
     * 获取老师列表-分页
     * @param page
     * @param vUserDto
     * @return
     */
    @GetMapping("getUserPage")
    public RES getUserPage(Page page, VUserDto vUserDto) {
        IPage teacherPage = userService.getUserPage(page, vUserDto);
        return RES.ok(CommonConstants.SUCCESS,"查询成功",teacherPage);
    }

    /**
     * 用户新增
     * @param vUserDto
     * @return
     */
    @PostMapping("addUser")
    public RES addUser(@RequestBody VUserDto vUserDto) {
        VUser oneByUserName = userService.getOneByUserName(vUserDto.getUserName());
        if (oneByUserName!= null){
            return RES.no(CommonConstants.FAIL,"用户已存在");
        }
        Integer flag = userService.addUser(vUserDto);
        if (flag>0){
            return RES.ok(CommonConstants.SUCCESS,"操作成功",null);
        }
        return RES.no(CommonConstants.FAIL,"操作失败");
    }

    @GetMapping("getInfoById/{userId}")
    public RES getInfoById(@PathVariable("userId") Integer userId) {
        VUser vUser = userService.getInfoById(userId);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",vUser);
    }

    @PutMapping("editUser")
    public RES editUser(@RequestBody VUser vUser) {
        VUser oneByUserName = userService.getOneByUserName(vUser.getUserName());
        if (oneByUserName!= null){
            if (vUser.getId() != oneByUserName.getId()){
                return RES.no(CommonConstants.FAIL,"用户名已存在");
            }
        }
        Integer flag = userService.editUser(vUser);
        if (flag>0){
            return RES.ok(CommonConstants.SUCCESS,"操作成功",null);
        }
        return RES.no(CommonConstants.FAIL,"操作失败");
    }

    @DeleteMapping("del/{userId}")
    public RES del(@PathVariable("userId") Integer userId) {
        Integer flag = userService.del(userId);
        if (flag==0){
            return RES.ok(CommonConstants.SUCCESS,"操作成功",null);
        }
        return RES.no(CommonConstants.FAIL,"操作失败");
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }
}
