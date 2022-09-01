package cn.study.config;

import cn.dev33.satoken.exception.*;
import cn.study.constant.CommonConstants;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class SaTokenExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public RES handlerNotLoginException(NotLoginException nle) {
        // 不同异常返回不同状态码
        String message = "";
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供Token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "未提供有效的Token";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "登录信息已过期，请重新登录";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "您的账户已在另一台设备上登录，如非本人操作，请立即修改密码";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "已被系统强制下线";
        } else if (nle.getType().equals(NotLoginException.NOT_TOKEN_MESSAGE)) {
            message = "未能读取到有效Token";
        }else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT_MESSAGE)) {
            message = "Token已过期";
        } else {
            message = "当前会话未登录";
        }
        // 返回给前端
        return RES.no( CommonConstants.TOKEN_FAIL, message);
    }

    @ExceptionHandler
    public RES handlerNotRoleException(NotRoleException e) {
        return RES.no(CommonConstants.PERMISSION_FAIL, "无此角色：" + e.getRole());
    }

    @ExceptionHandler
    public RES handlerNotPermissionException(NotPermissionException e) {
        return RES.no(CommonConstants.PERMISSION_FAIL, "无此权限");
    }

    @ExceptionHandler
    public RES handlerDisableLoginException(DisableLoginException e) {
        return RES.no( CommonConstants.TOKEN_FAIL, "账户被封禁：" + e.getDisableTime() + "秒后解封");
    }

    @ExceptionHandler
    public RES handlerNotSafeException(NotSafeException e) {
        return RES.no( CommonConstants.TOKEN_FAIL, "二级认证异常：" + e.getMessage());
    }
}
