package com.smbms.controller;

import com.github.pagehelper.PageInfo;
import com.smbms.pojo.Bill;
import com.smbms.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @RequestMapping("/billadd.do")
    public String billadd(){
        return "billadd";
    }
    //查询订单列表
    @RequestMapping("/bill.do")
    public String selectBillList(Model model,String pageIndex, String queryProductName, String queryProviderId, String queryIsPayment){
        PageInfo<Bill> pageInfo = billService.selectBillList(pageIndex, 5, queryProductName, queryProviderId, queryIsPayment);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("queryProductName",queryProductName);
        model.addAttribute("queryProviderId",queryProviderId);
        model.addAttribute("queryIsPayment",queryIsPayment);
        return "billlist";
    }
    //查询订单详情
    @RequestMapping("/view.do")
    public String selectBillView(String billid,Model model){
        Bill bill = billService.selectBillView(billid);
        model.addAttribute("bill",bill);
        return "billview";
    }
    //查询订单详情并跳转到订单修改页面
    @RequestMapping("/modify.do")
    public String selectBillModify(String billid,Model model){
        Bill bill = billService.selectBillView(billid);
        model.addAttribute("bill",bill);
        return "billmodify";
    }
    //更新订单
    @PostMapping("/upbill.do")
    public String updateBill(Bill bill){
        int i = billService.updateBillModify(bill);
        return "redirect:/bill/bill.do";
    }
    //添加订单
    @RequestMapping("/addbill.do")
    public String insertBill(Bill bill){
        int i = billService.insertBill(bill);
        return "redirect:/bill/bill.do";
    }
    //删除订单
    @RequestMapping("/delBill.do")
    @ResponseBody
    public Map<String,String> delBill(String billid){
        int i = billService.deleteBill(billid);
        Map<String,String> map = new HashMap<>();
        String delResult = "";
        if(i>0){
            delResult="true";
        }else if(i==0){
            delResult="false";
        }else {
            delResult="notexist";
        }
        map.put("delResult",delResult);
        return map;
    }
}
