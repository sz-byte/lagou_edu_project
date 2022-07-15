package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    /*
    * 用户分页&多条件查询
    * */
    public PageInfo findAllUserByPage(UserVO userVO);

    /*
     *修改用户状态
     * */
    public void updateUserStatus(@Param("id")  int id, @Param("status") String status);

    /*
    * 用户登录
    * */
    public User login(User user) throws Exception;

    /*
     * 分配角色
     * */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
    * 用户关联角色
    * */
    public void userContextRole(UserVO userVO);

    /*
    * 获取用户权限，进行菜单展示
    * */
    public ResponseResult getUserPermission(Integer userid);

    /*
    * 查询指定用户
    * */
    public User selectUser(String name);

}
