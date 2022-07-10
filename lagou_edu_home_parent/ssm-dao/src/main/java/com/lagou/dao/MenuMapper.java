package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /*
    * 查询所有父子集菜单信息
    * */
    public List<Menu> findSubMenuListByPid(int pid);

    /*
    * 查询所有菜单列表
    * */
    public List<Menu> findAllMenu();


    Menu finMenuById(Integer id);
}
