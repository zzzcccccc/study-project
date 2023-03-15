package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VUserDto;
import cn.study.entity.VUser;
import cn.study.service.UserService;
import cn.study.vo.VUserVo;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user/")
@Api(value = "用户管理",tags = "用户管理")
public class UserController {

    @Resource
    UserService userService;

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @ApiOperation(value = "登录",notes = "登录")
    @GetMapping("doLogin")
    public RES doLogin(@ApiParam("用户名") String userName,@ApiParam("密码") String password) {
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
    @ApiOperation(value = "退出登录",notes = "退出登录")
    @GetMapping("logOut/{loginId}")
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
    @ApiOperation(value = "获取老师列表-分页",notes = "获取老师列表-分页")
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
    @ApiOperation(value = "用户新增",notes = "用户新增")
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

    @ApiOperation(value = "根据用户ID获取",notes = "根据用户ID获取")
    @GetMapping("getInfoById/{userId}")
    public RES getInfoById(@PathVariable("userId") Integer userId) {
        VUserVo infoById = userService.getInfoById(userId);
        if(infoById.getClassIds()!=null){
            String classIds = infoById.getClassIds().toString();
            infoById.setClassIds(JSON.parse(classIds));
        }else{
            Integer[] arr  = new  Integer[]{};
            infoById.setClassIds(arr);
        }

        String chooseRoleIds = infoById.getRoleIds().toString();
        if (chooseRoleIds!=null){
            infoById.setRoleIds(JSON.parse(chooseRoleIds));
        }

        return RES.ok(CommonConstants.SUCCESS,"操作成功",infoById);
    }

    @ApiOperation(value = "根据用户名获取",notes = "根据用户名获取")
    @SaCheckLogin
    @GetMapping("getInfoByUsername/{username}")
    public RES getInfoByUsername(@PathVariable("username") String username) {
        VUser vUser = userService.getInfoByUsername(username);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",vUser);
    }

    @ApiOperation(value = "修改用户",notes = "修改用户")
    @SaCheckLogin
    @SaCheckPermission(value = "user:edit", orRole = {"admin","teacher"})
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

    @ApiOperation(value = "删除用户",notes = "删除用户")
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
    @ApiOperation(value = "查询登录状态",notes = "查询登录状态")
    @GetMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @ApiOperation(value = "查询 Token 信息 ",notes = "查询 Token 信息 ")
    @GetMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }
}
