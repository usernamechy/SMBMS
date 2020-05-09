package com.smbms.service;

import com.github.pagehelper.PageInfo;
import com.smbms.pojo.Role;
import com.smbms.pojo.User;
import com.smbms.util.PageUtil;

import java.util.List;

public interface UserService {
    /**
     * 用户登录
     * @param userCode
     * @param userPassword
     * @return
     */
    User login(String userCode,String userPassword);

    /**
     * 查询用户列表
     * @param pageSize
     * @param pageNum
     * @param queryname
     * @param queryUserRole
     * @return
     */
    PageInfo<User> selectUserByList(int pageNum,int pageSize,String queryname, String queryUserRole);

    /**
     * 查询用户角色
     * @return
     */
    List<Role> selectByRole();

    /**
     * 查看用户详情
     * @param id
     * @return
     */
    User userView(long id);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 验证用户code
     * @param userCode
     * @return
     */
    User selectUserByCode(String userCode);

    /**
     * 根据id删除用户
     * @param uid
     * @return
     */
    int deleteUser(String uid);
}
