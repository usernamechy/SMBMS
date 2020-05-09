package com.smbms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.smbms.exception.UserException;
import com.smbms.pojo.Role;
import com.smbms.pojo.User;
import com.smbms.pojo.UserExample;
import com.smbms.service.PwdService;
import com.smbms.service.UserService;
import com.smbms.util.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PwdService pwdService;
    //跳转页面
    @RequestMapping("/pwdmodify.do")
    public String pwdModify(){
        return "pwdmodify";
    }
    @RequestMapping("/useradd.do")
    public String userAdd(){
        return "useradd";
    }
    //查询用户列表
    @RequestMapping(value = "/userlist.do")
    public String selectUserByList(String pageIndex,String queryname, String queryUserRole,Model model){
        Integer pageNum=1;
        if(pageIndex!=null){
            pageNum=Integer.parseInt(pageIndex);
        }
        PageInfo<User> pageInfo = userService.selectUserByList(pageNum, 5, queryname, queryUserRole);
        List<Role> roles = userService.selectByRole();
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("roleList",roles);
        model.addAttribute("queryUserName",queryname);
        model.addAttribute("queryUserRole",queryUserRole);
        return "userlist";
    }

    //验证密码
    @RequestMapping(value = "/querypwd.do",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> queryPwd(String oldpassword, HttpSession session, HttpServletResponse resp) throws IOException {
        User user = (User) session.getAttribute("loginUser");
        String userName = user.getUserName();
        User queryPwd = pwdService.queryPwd(userName, oldpassword);
        Map<String, String> map = new HashMap<>();
        System.out.println(oldpassword);
        String result="";
        if (queryPwd==null) {
            result="sessionerror";
        }else if (user.getUserPassword().equals(oldpassword)) {
            result="true";
        }else {
            result="false";
        }
        map.put("result",result);
        return map;
    }
    //修改密码
    @PostMapping("/upwd.do")
    public String uPwd(String newpassword,HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        String userName = user.getUserName();
        int i = pwdService.updatePwd(userName, newpassword);
        if (i>0){
            return "redirect:/login.jsp";
        }
        return "error";
    }
    //查询用户详情
    @RequestMapping("/view.do")
    public String userView(Model model,String uid){
        long id = 0L;
        if(uid!=null){
            id=Long.parseLong(uid);
        }
        User user = userService.userView(id);
        model.addAttribute("user",user);
        return "userview";
    }
    //用户角色
    @RequestMapping(value = "/userRole.do",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String userRole(){
        List<Role> roles = userService.selectByRole();
        String role = JSON.toJSONString(roles);
        return role;
    }
    //跳转到修改页面同时查询用户详情
    @RequestMapping("/modify")
    public String userModify(Model model,String uid){
        long id = 0L;
        if(uid!=null){
            id=Long.parseLong(uid);
        }
        User user = userService.userView(id);
        model.addAttribute("user",user);
        return "usermodify";
    }
    //修改用户
    @RequestMapping("/upuser.do")
    public String upUser(User user){
        int i = userService.updateUser(user);
        if (i>0){
            return "redirect:/user/userlist.do";
        }
        return "error";
    }
    //添加用户
    @RequestMapping("/addUser.do")
    public String addUser(User user){
        int i = userService.insertUser(user);
        return "redirect:/user/userlist.do";
    }
    //验证userCode
    @RequestMapping("/userCode.do")
    @ResponseBody
    public Map<String,String> userCodeVerify(String userCode){
        User user = userService.selectUserByCode(userCode);
        Map<String,String> map = new HashMap<>();
        String usercode="";
        if (user!=null){
            usercode="exist";
        }else {
            usercode="";
        }
        map.put("userCode",usercode);
        return map;
    }
    //删除用户
    @RequestMapping("/deleteuser.do")
    @ResponseBody
    public Map<String,String> deleteUser(String uid){
        int i = userService.deleteUser(uid);
        Map<String,String> map = new HashMap<>();
        String delResult = "";
        if(i>0){
            delResult="true";
        }else if(i==0){
            delResult="false";
        }else{
            delResult="notexist";
        }
        map.put("delResult",delResult);
        return map;
    }
}
