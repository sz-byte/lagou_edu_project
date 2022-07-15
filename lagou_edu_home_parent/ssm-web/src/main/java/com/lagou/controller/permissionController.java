package com.lagou.controller;

import com.lagou.domain.ResponseResult;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/permission")
public class permissionController {
    @Autowired
    private UserService userService;


    /*
     * 获取用户权限，进行菜单动态展示
     * */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的token
        String header_token = request.getHeader("Authorization");

        //获取session中的token
        String session_token =(String) request.getSession().getAttribute("access_token");

        //判断token是否一致
        if(header_token.equals(session_token)){
            //获取用户id
            Integer user_id =(Integer) request.getSession().getAttribute("user_id");
            //调用service，进行菜单信息查询
            ResponseResult responseResult = userService.getUserPermission(user_id);

            return responseResult;
        }else{
            ResponseResult responseResult = new ResponseResult(false, 400, "获取菜单信息失败", null);
            return responseResult;
        }
    }
}
