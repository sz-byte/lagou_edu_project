package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {

   /*
   * 查询角色列表（查询）
   * */
    public List<Role> findAllRole(Role role);

    /*
    * 添加角色
    * */
    public void saveRole(Role role);

    /*
    * 修改角色
    * */
    public void updateRole(Role role);

    /*
    * 根据角色ID查询该角色关联的菜单信息
    * */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /*
    * 根据roleid清空中间表的关联关系
    * */
    public void deleteRoleContextMenu(Integer rid);

    /*
    * 为角色分配菜单信息
    * */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
    * 删除角色
    * */
    public void deleteRole(Integer roleid);

    /*
    * 获取当前角色拥有的资源分类信息
    * */
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer id);

    /*
    * 获取当前角色拥有的资源信息
    *
    * */
    public List<Resource> findResourceListByRoleId(Integer id);

    /*
    * 根据角色id删除角色与资源关联关系
    * */
    public void deleteRoleResource(Integer id);
    /*
    * 为角色分配资源
    * */
    public void RoleResource(RoleResourceRelation roleResourceRelation);
}
