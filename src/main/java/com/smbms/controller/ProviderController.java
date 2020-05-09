package com.smbms.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.smbms.pojo.Provider;
import com.smbms.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;
    //跳转请求
    @RequestMapping("/provideradd.do")
    public String providerAddSkip(){
        return "provideradd";
    }
    //查询供应商列表
    @RequestMapping("/provider.do")
    public String selectProviderList(String pageIndex, String queryProCode, String queryProName, Model model){
        PageInfo<Provider> pageInfo = providerService.selectProviderList(pageIndex, 5, queryProCode, queryProName);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("queryProCode",queryProCode);
        model.addAttribute("queryProName",queryProName);
        return "providerlist";
    }
    @RequestMapping(value = "/providerList.do",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectProvider(){
        List<Provider> providers = providerService.selectProvider();
        String data = JSON.toJSONString(providers);
        return data;
    }
    //查询供应商详情
    @RequestMapping("/view.do")
    public String selectProviderView(String proid,Model model){
        Provider provider = providerService.selectProviderView(proid);
        model.addAttribute("provider",provider);
        return "providerview";
    }
    //查询供应商详情,跳转到供应商修改页
    @RequestMapping("/modify.do")
    public String selectProviderModify(String proid,Model model){
        Provider provider = providerService.selectProviderView(proid);
        model.addAttribute("provider",provider);
        return "providermodify";
    }
    //修改供应商
    @PostMapping("/upProvider.do")
    public String upProvider(Provider provider){
        int i = providerService.updateProvider(provider);
        return "redirect:/provider/provider.do";
    }
    //添加供应商
    @RequestMapping("/addProvider.do")
    public String addProvider(Provider provider){
        int i = providerService.insertProvider(provider);
        return "redirect:/provider/provider.do";
    }
    //删除供应商
    @RequestMapping("/delProvider.do")
    @ResponseBody
    public Map<String,String> delProvider(String proid){
        int i = providerService.deleteProvider(proid);
        Map<String, String> map = new HashMap<>();
        String delResult = "";
        if(i>0){
            delResult = "true";
        }else if(i==0){
            delResult = "false";
        }else{
            delResult = "notexist";
        }
        map.put("delResult",delResult);
        return map;
    }
}
