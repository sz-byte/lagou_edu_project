package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {

    /*
    * 查询所有角色(条件)
    * */
    public List<Role> findAllRole(Role role);

    /*
    * 新增角色
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
    * 为角色分配菜单
    * */
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    /*
     * 删除角色
     * */
    public void deleteRole(Integer roleid);

    /*
     * 获取当前角色拥有的资源分类信息
     * */
    public List<ResourceCategory> findResourceListByRoleId(Integer id);

    /*
    * 分配资源
    * */
    public void roleContextResource(RoleResourceVO roleResourceVO);


}
