package com.smbms.dao;

import com.smbms.pojo.User;
import com.smbms.pojo.UserExample;
import com.smbms.util.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    User selectUserview(Long id);

    List<User> selectUserByList(@Param("userName") String userName,@Param("userRole") String userRole);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updatePwdByUser(@Param("userName") String userName,@Param("userPassword") String userPassword);
}