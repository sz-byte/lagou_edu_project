package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /*
    * 查询所有菜单信息
    * */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();

        ResponseResult responseResult = new ResponseResult(true,200,"查询所有菜单信息成功",allMenu);
        return responseResult;
    }

    /*
    * 回显菜单信息
    * */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        //根据id的值判断当前是更新还是添加操作
        if(id == -1){
            //添加
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"添加回显成功",map);
        }else {
            //修改操作 回显所有menu信息
            Menu menu= menuService.findMenuById(id);

            //添加
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);
            return new ResponseResult(true,200,"修改回显成功",map);
        }
    }

}
