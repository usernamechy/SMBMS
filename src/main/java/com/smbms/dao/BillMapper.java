package com.smbms.dao;

import com.smbms.pojo.Bill;
import com.smbms.pojo.BillExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    long countByExample(BillExample example);

    int deleteByExample(BillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Bill record);

    int insertSelective(Bill record);

    List<Bill> selectByExample(BillExample example);

    List<Bill> selectBillList(@Param("productName") String productName,@Param("providerId") String providerId,@Param("isPayment") String isPayment);

    Bill selectByPrimaryKey(Long id);

    Bill selectBillView(Long id);

    int updateByExampleSelective(@Param("record") Bill record, @Param("example") BillExample example);

    int updateByExample(@Param("record") Bill record, @Param("example") BillExample example);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);
}