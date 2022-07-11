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


    public Menu finMenuById(Integer id);

    /*
    * 新增菜单
    * */
    public void saveMenu(Menu menu);
    /*
    * 修改菜单
    * */
    public void updateMenu(Menu menu);
}
