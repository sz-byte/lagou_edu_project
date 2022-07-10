package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllUserByPage(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",allRole);
        return responseResult;
    }

    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){

        System.out.println(role);

        if(role.getId() == null){
            //新增
            roleService.saveRole(role);
            return new ResponseResult(true,200,"新增角色成功",null);
        }else {
            //修改
            roleService.updateRole(role);
            return new ResponseResult(true,200,"修改角色成功",null);
        }
    }

    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){

        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有的父子级菜单", map);

        return responseResult;
    }

    /*
    * 根据角色ID查询关联的菜单信息
    * */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> menuByRileId = roleService.findMenuByRoleId(roleId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询角色关联的菜单信息成功", menuByRileId);

        return responseResult;

    }

    /*
    * 为角色分配菜单
    * */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVO roleMenuVO){

        roleService.roleContextMenu(roleMenuVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
        return responseResult;
    }

    /*
    * 输出角色
    * */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){

        roleService.deleteRole(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", null);
        return responseResult;

    }

}
