package com.smbms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smbms.dao.BillMapper;
import com.smbms.pojo.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;
    //查询订单列表
    @Override
    public PageInfo<Bill> selectBillList(String pageNum, int pageSize, String queryProductName, String queryProviderId, String queryIsPayment) {
        int pageIndex=1;
        if(pageNum!=null){
            pageIndex = Integer.parseInt(pageNum);
        }
        PageHelper.startPage(pageIndex,pageSize);
        List<Bill> bills = billMapper.selectBillList(queryProductName, queryProviderId, queryIsPayment);
        PageInfo<Bill> pageInfo = new PageInfo<>(bills);
        return pageInfo;
    }
    //查询订单详情
    @Override
    public Bill selectBillView(String billid) {
        Long id = 0L;
        if(billid!=null){
            id = Long.parseLong(billid);
        }
        Bill bill = billMapper.selectBillView(id);
        return bill;
    }
    //更新订单
    @Override
    public int updateBillModify(Bill bill) {
        int i = billMapper.updateByPrimaryKeySelective(bill);
        return i;
    }
    //添加订单
    @Override
    public int insertBill(Bill bill) {
        int i = billMapper.insertSelective(bill);
        return i;
    }
    //删除订单
    @Override
    public int deleteBill(String billid) {
        Long id = 0L;
        if(billid!=null){
            id = Long.parseLong(billid);
        }
        int i = billMapper.deleteByPrimaryKey(id);
        return i;
    }
}
