package com.smbms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smbms.dao.ProviderMapper;
import com.smbms.pojo.Provider;
import com.smbms.pojo.ProviderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderMapper providerMapper;
    //查询供应商列表
    @Override
    public PageInfo<Provider> selectProviderList(String pageNum, int pageSize, String queryProCode, String queryProName) {
        int pageIndex = 1;
        if (pageNum!=null){
            pageIndex = Integer.parseInt(pageNum);
        }
        PageHelper.startPage(pageIndex,pageSize);
        List<Provider> providers = providerMapper.selectProviderList(queryProCode,queryProName);
        PageInfo<Provider> pageInfo = new PageInfo<>(providers);
        return pageInfo;
    }
    //异步请求
    @Override
    public List<Provider> selectProvider() {
        ProviderExample example = new ProviderExample();
        List<Provider> providers = providerMapper.selectByExample(example);
        return providers;
    }

    //查询供应商详情
    @Override
    public Provider selectProviderView(String proid) {
        Long id = 0L;
        if (proid!=null){
            id = Long.parseLong(proid);
        }
        Provider provider = providerMapper.selectByPrimaryKey(id);
        return provider;
    }
    //修改供应商
    @Override
    public int updateProvider(Provider provider) {
        int i = providerMapper.updateByPrimaryKeySelective(provider);
        return i;
    }
    //添加供应商
    @Override
    public int insertProvider(Provider provider) {
        int i = providerMapper.insertSelective(provider);
        return i;
    }
    //删除供应商
    @Override
    public int deleteProvider(String proid) {
        Long id = 0L;
        if(proid!=null){
             id = Long.parseLong(proid);
        }
        int i = providerMapper.deleteByPrimaryKey(id);
        return i;
    }
}
