package com.smbms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smbms.dao.RoleMapper;
import com.smbms.dao.UserMapper;
import com.smbms.pojo.Role;
import com.smbms.pojo.RoleExample;
import com.smbms.pojo.User;
import com.smbms.pojo.UserExample;
import com.smbms.util.DateAgeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    //登录
    @Override
    public User login(String userCode, String userPassword) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(userCode);
        criteria.andUserPasswordEqualTo(userPassword);
        List<User> users = userMapper.selectByExample(example);
        if (users.size()>0){
            return users.get(0);
        }
        return null;
    }
    //显示用户列表
    @Override
    public PageInfo<User> selectUserByList(int pageNum,int pageSize,String queryname, String queryUserRole) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.selectUserByList(queryname, queryUserRole);
        if(list!=null) {
            for (User user : list) {
                try {
                    user.setAge(DateAgeUtil.getAgeByBirth(user.getBirthday()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    //查询用户角色
    @Override
    public List<Role> selectByRole() {
        RoleExample roleExample = new RoleExample();
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return roles;
    }
    //查询用户详情
    @Override
    public User userView(long id) {
        User user = userMapper.selectUserview(id);
        return user;
    }
    //修改用户
    @Override
    public int updateUser(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(user.getId());
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i;
    }
    //添加用户
    @Override
    public int insertUser(User user) {
        int i = userMapper.insertSelective(user);
        return i;
    }
    //验证用户code
    @Override
    public User selectUserByCode(String userCode) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(userCode);
        List<User> users = userMapper.selectByExample(example);
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }
    //删除用户
    @Override
    public int deleteUser(String uid) {
        long id= 0L;
        if (uid!=null){
            id = Long.parseLong(uid);
        }
        int i = userMapper.deleteByPrimaryKey(id);
        return i;
    }
}
