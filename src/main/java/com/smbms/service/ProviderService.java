package com.smbms.service;

import com.github.pagehelper.PageInfo;
import com.smbms.pojo.Provider;

import java.util.List;

public interface ProviderService {
    /**
     * 查询供应商列表
     * @param pageNum
     * @param pageSize
     * @param queryProCode
     * @param queryProName
     * @return
     */
    PageInfo<Provider> selectProviderList(String pageNum,int pageSize,String queryProCode,String queryProName);

    /**
     * 返回json数据
     * @return
     */
    List<Provider> selectProvider();
    /**
     * 查询供应详情
     * @param proid
     * @return
     */
    Provider selectProviderView(String proid);

    /**
     * 修改供应商
     * @param provider
     * @return
     */
    int updateProvider(Provider provider);

    /**
     * 删除供应商
     * @param provider
     * @return
     */
    int insertProvider(Provider provider);

    /**
     * 删除供应商
     * @param proid
     * @return
     */
    int deleteProvider(String proid);
}

