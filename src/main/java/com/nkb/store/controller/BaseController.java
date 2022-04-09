package com.nkb.store.controller;


import com.nkb.store.common.Result;
import com.nkb.store.controller.ex.*;
import com.nkb.store.service.ex.*;
import com.nkb.store.service.ex.FileUploadException;
import com.nkb.store.common.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 在其中定义表示响应成功的状态码及统一处理异常的方法。
 */
public class BaseController {
    /** 操作成功的状态码 */
    public static final int OK = 200;

    /** @ExceptionHandler用于统一处理方法抛出的异常 */
    @ExceptionHandler({ServiceException.class, FileUploadException.class}) // 用于统一处理抛出的异常
    public Result<Void> handleException(Throwable e) {
        Result<Void> result = new Result<Void>(e);

        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMsg("用户名被占用");
        }else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMsg("用户数据不存在的异常");
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMsg("用户名的密码错误的异常");
        }else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMsg("用户的收货地址超出上限的异常");
        }else if (e instanceof AddressNotFoundException) {
            result.setState(4004);
            result.setMsg("收货地址数据不存在的异常");
        }else if (e instanceof AccessDeniedException) {
            result.setState(4005);
            result.setMsg("/** 非法访问的异常 */");
        }else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMsg("/** 商品数据不存在的异常 */");
        }else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMsg("/** 购物车数据不存在的异常 */");
        }else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMsg("插入数据时产生未知的异常");
        }else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMsg("更新数据时产生未知的异常");
        }else if (e instanceof DeleteException) {
            result.setState(5002);
            result.setMsg("删除数据失败的异常");
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
        } else if (e instanceof FileStateException) {
            result.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录的用户uid的值
     */
    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的username
     * @param session session对象
     * @return 当前登录用户的用户名
     *
     * 在实现类中重写父类的toString()，
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
