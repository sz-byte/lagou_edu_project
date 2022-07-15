package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public void saveRole(Role role) {

        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("System");
        role.setUpdatedBy("System");
        roleMapper.saveRole(role);

    }

    @Override
    public void updateRole(Role role) {

        role.setUpdatedTime(new Date());
        role.setUpdatedBy("System");
        roleMapper.updateRole(role);
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {

        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {

        //1.清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());

        //2.为角色分配菜单
        for (Integer mid:roleMenuVO.getMenuIdList()) {

            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer roleid) {
        //调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleid);
        roleMapper.deleteRole(roleid);
    }



    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer id) {
        List<Resource> resourceList = roleMapper.findResourceListByRoleId(id);
        List<ResourceCategory> categoryList = roleMapper.findResourceCategoryByRoleId(id);

        for (ResourceCategory category:categoryList) {
            ArrayList<Resource> list = new ArrayList<>();
            for (Resource resource:resourceList) {
                if(resource.getCategoryId() == category.getId()){
                    list.add(resource);
                }
            }
            category.setResourceList(list);
        }
        return categoryList;
    }

    @Override
    public void roleContextResource(RoleResourceVO roleResourceVO) {
        roleMapper.deleteRoleResource(roleResourceVO.getRoleId());

        for (Integer resourceId:roleResourceVO.getResourceIdList()) {
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setResourceId(resourceId);
            roleResourceRelation.setRoleId(roleResourceVO.getRoleId());

            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setUpdatedBy("system");
            roleResourceRelation.setCreatedBy("system");

            roleMapper.RoleResource(roleResourceRelation);
        }

    }


}
