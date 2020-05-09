package com.smbms.service;

import com.smbms.dao.UserMapper;
import com.smbms.pojo.User;
import com.smbms.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PwdServiceImpl implements PwdService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryPwd(String userName, String userPassword) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        criteria.andUserPasswordEqualTo(userPassword);
        List<User> userList = userMapper.selectByExample(example);
        if (userList!=null){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public int updatePwd(String userName, String userPassword) {
        int i = userMapper.updatePwdByUser(userName, userPassword);
        return i;
    }
}
