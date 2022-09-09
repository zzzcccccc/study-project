package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.SaTokenInfo;
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
import com.alibaba.fastjson.JSON;
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
            // 第2步，获取 Token  相关参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return RES.ok(CommonConstants.SUCCESS,"登录成功",tokenInfo);
        }else if (flag==1){
            return RES.no(CommonConstants.FAIL,"用户名或密码错误");
        }
        return RES.no(CommonConstants.FAIL,"用户不存在");
    }

    /**
     * 退出登录
     * @param loginId
     * @return
     */
    @RequestMapping("logOut/{loginId}")
    public RES logOut(@PathVariable(value = "loginId") String loginId) {
        StpUtil.logout(loginId);
        if (!StpUtil.isLogin()){
            return RES.ok(CommonConstants.SUCCESS,"操作成功",null);
        }
        return RES.no(CommonConstants.FAIL,"操作失败");
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
    @SaCheckLogin
    @SaCheckPermission(value = "user:add", orRole = "admin")
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
        VUserVo infoById = userService.getInfoById(userId);
        String classIds = infoById.getClassIds();
        if(classIds!=null){
            infoById.setClassIdArray(JSON.parse(classIds));
        }else{
            Integer[] arr  = new  Integer[]{};
            infoById.setClassIdArray(arr);
        }
        return RES.ok(CommonConstants.SUCCESS,"操作成功",infoById);
    }

    @SaCheckLogin
    @GetMapping("getInfoByUsername/{username}")
    public RES getInfoByUsername(@PathVariable("username") String username) {
        VUser vUser = userService.getInfoByUsername(username);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",vUser);
    }

    @SaCheckLogin
    @SaCheckPermission(value = "user:edit", orRole = "admin")
    @PutMapping("editUser")
    public RES editUser(@RequestBody VUserDto vUserDto) {
        VUser oneByUserName = userService.getOneByUserName(vUserDto.getUserName());
        if (oneByUserName!= null){
            if (vUserDto.getId() != oneByUserName.getId()){
                return RES.no(CommonConstants.FAIL,"用户名已存在");
            }
        }
        Integer flag = userService.editUser(vUserDto);
        if (flag>0){
            return RES.ok(CommonConstants.SUCCESS,"操作成功",null);
        }
        return RES.no(CommonConstants.FAIL,"操作失败");
    }

    @SaCheckLogin
    @SaCheckPermission(value = "user:del", orRole = "admin")
    @DeleteMapping("del/{userId}")
    public RES del(@PathVariable("userId") Integer userId) {
        Integer flag = userService.del(userId);
        if (flag==0){
            return RES.ok(CommonConstants.SUCCESS,"删除成功",null);
        }
        return RES.no(CommonConstants.FAIL,"删除失败");
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
