package com.nkb.store.controller;

import com.nkb.store.common.Base64Utils;
import com.nkb.store.common.JsonResult;
import com.nkb.store.common.Result;
import com.nkb.store.controller.ex.*;
import com.nkb.store.entity.User;
import com.nkb.store.service.UserService;
import com.nkb.store.service.ex.InsertException;
import com.nkb.store.service.ex.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController // @Controller + @ReponseBody
@RequestMapping("user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    // 设置上传文件的最大值
    public static final  int AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/gif");
    }

    @RequestMapping("/reg")
    public Result<?> reg(User user){

        userService.reg(user);

        return Result.success();
    }

    @RequestMapping("/login")
    public Result<User> login(String username, String password, String auto, HttpSession session, HttpServletResponse response){
        User user = userService.login(username,password);

        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());
        System.out.println(auto);

        if(auto ==null){
            Cookie cookie = new Cookie("autoUser","");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }else {
            String content = username+":"+password;
            content = Base64Utils.encode(content);
//            System.out.println(content);
            Cookie cookie = new Cookie("autoUser", content);
            cookie.setPath("/");
            cookie.setMaxAge(14*24*60*60);
            response.addCookie(cookie);
        }

        return Result.success(user);
    }

    @RequestMapping("change_password")
    public Result<?> changePassword(String oldPassword,
                                    String newPassword,
                                    HttpSession session){

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        // 调用业务对象执行修改密码
        userService.changePawword(uid,username,oldPassword,newPassword);;
        // 返回成功
        return Result.success();
    }

    @RequestMapping("get_by_uid")
    public Result<User> getByUid(HttpSession session){
        // 从HttpSession对象中获取uid , 调用业务对象执行获取数据
        User data = userService.getByUid(getUidFromSession(session));
        return Result.success(data);
    }

    @RequestMapping("change_info")
    public Result<?> changeInfo(User user, HttpSession session){
        // user对象有四部分数据：username、phone、email、gender
        // uid 数据需要再次封装到user对象中
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行修改用户资料
        userService.changeInfo(uid,username,user);
        return Result.success();
    }

    @RequestMapping("change_avatar")
    public Result<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file){
        // 判断文件是否为空
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        // 判断文件的类型是否为我们规定的类型
        String contentType = file.getContentType();
        if (!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }
        // 上传的文件 ../upload/文件.png
        String parent = session.getServletContext().getRealPath("upload");
        // File对象指向这个路径，File是否存在
        File dir = new File(parent);
        if (!dir.exists()){ // 检测路径是否存在
            dir.mkdir();// 创建当前的目录
        }

        // 获取到这个文件的名称，UUID工具将生成一个新的字符串作文文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println("原始名称：" + originalFilename);

        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;

        File dest = new File(dir,filename);// 是一个空文件
        // 参数file中数据写入到这个文件中
        try {
            file.transferTo(dest); // 将file文件中的数据，写入到dest文件中，前提条件文件的后缀一致
        } catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        // 返回头像的路径
        String avatar = "/upload/" + filename;
        System.out.println(avatar);
        userService.changeAvatar(uid,username,avatar);
        // 返回用户头像的路径给前端页面，将用于头像展示使用
        return Result.success(avatar);
    }

}
