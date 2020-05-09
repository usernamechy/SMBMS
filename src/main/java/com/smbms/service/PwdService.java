package com.smbms.service;

import com.smbms.pojo.User;

public interface PwdService {

    //查询密码
    User queryPwd(String userName,String userPassword);

    //修改密码
    int updatePwd(String userName,String userPassword);
}
