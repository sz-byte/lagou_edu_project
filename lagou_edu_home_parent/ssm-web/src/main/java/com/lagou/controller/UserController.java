package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    *用户分页&多条件组合查询
    * */

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){

        PageInfo pageInfo = userService.findAllUserByPage(userVO);
        return new ResponseResult(true,200,"分页条件查询成功",pageInfo);
    }

    /*
    * 修改用户状态
    * */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id, @RequestParam String status){
        if("ENABLE".equals(status)){
            status = "DISABLE";
        }else{
            status = "ENABLE";
        }
        userService.updateUserStatus(id, status);
        return new ResponseResult(true,200,"修改状态成功",status);
    }

    /*
    * 用户登录
    * */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);

        if(user1 != null){

            //保存用户id及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            User selectUser = userService.selectUser(user.getPhone());
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            //将查询出的数据响应给前台
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user",selectUser);
            map.put("user_id",user1.getId());

            return new ResponseResult(true,1,"登录成功",map);

        }else{
            return new ResponseResult(true,400,"用户名或密码错误",null);
        }
    }

    /*
    * 分配角色回显
    * */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> roleList = userService.findUserRelationRoleById(id);

        return new ResponseResult(true,200,"分配角色回显成功",roleList);
    }

    /*
    * 分配角色
    * */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){

        userService.userContextRole(userVO);

        return new ResponseResult(true,200,"分配角色成功",null);
    }



}
