package com.smbms.service;

import com.github.pagehelper.PageInfo;
import com.smbms.pojo.Bill;

public interface BillService {
    /**
     * 查询订单列表
     * @param pageNum
     * @param pageSize
     * @param queryProductName
     * @param queryProviderId
     * @param queryIsPayment
     * @return
     */
    PageInfo<Bill> selectBillList(String pageNum,int pageSize,String queryProductName,String queryProviderId,String queryIsPayment);

    /**
     * 查询订单详情
     * @param billid
     * @return
     */
    Bill selectBillView(String billid);

    /**
     * 更新订单
     * @param bill
     * @return
     */
    int updateBillModify(Bill bill);

    /**
     * 添加订单
     * @param bill
     * @return
     */
    int insertBill(Bill bill);

    /**
     * 删除订单
     * @param billid
     * @return
     */
    int deleteBill(String billid);
}
