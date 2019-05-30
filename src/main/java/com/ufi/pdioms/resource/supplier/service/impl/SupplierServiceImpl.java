package com.ufi.pdioms.resource.supplier.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ufi.pdioms.resource.common.model.ErrorCode;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.common.model.PageResult;
import com.ufi.pdioms.resource.supplier.dao.SupplierDao;
import com.ufi.pdioms.resource.supplier.model.Supplier;
import com.ufi.pdioms.resource.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * 供应商业务层实现类
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    /**
     * 获得供应商所有列表信息
     *
     * @param pageNo   分页起始页
     * @param pageSize 分页页大小
     * @param search    条件搜索的供应商
     * @return 返回搜索结果集
     */
    @Override
    public PageResult getSupplierInfo(Integer pageNo, Integer pageSize,String search) {
        PageHelper.startPage(pageNo, pageSize); //分页参数设置
        //判断搜索参数不等于空或者不等于空字符
        if (StringUtil.isEmpty(search)) {
            Example example = new Example(Supplier.class);
            example.createCriteria().andEqualTo("isDelete", 0);
            List<Supplier> suppliers = supplierDao.selectByExample(example);
            PageInfo<Supplier> pageInfo = new PageInfo<>(suppliers);
            PageResult result = new PageResult(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
            return result;
        }
        //供应商搜索条件查询《不》等于空的情况下，进行搜索分页查询
        Example example = new Example(Supplier.class);
        Example.Criteria criteria = example.createCriteria();
        if (!search.equals("")) criteria.andEqualTo(searchIf(search), search);  //搜索框中的内容进行搜索的条件筛选
        criteria.andEqualTo("isDelete", 0);

        List<Supplier> suppliers = supplierDao.selectByExample(example);
        PageInfo<Supplier> pageInfo = new PageInfo<>(suppliers);
        PageResult result = new PageResult(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }

    /****这个是判断搜索中是对应那个字段进行条件筛选的****/
    private String searchIf(String search) {
        int supplier = supplierDao.findSupplierCount(search);
        int contacts = supplierDao.findContactsCount(search);
        if (supplier >= 1) return "supplier";
        if (contacts >= 1) {
            return "contacts";
        } else {
            return " ";
        }
    }

    /**
     * 新增供应商信息
     *
     * @param supplier 供应商对象信息
     * @param result   结果说明
     */
    @Transactional
    public void addSupplierInfo(Supplier supplier, GeneralResult result)
    {
        Example example = new Example(Supplier.class);
        example.createCriteria().andEqualTo("isDelete", 0);
        List<Supplier> supplierList = supplierDao.selectByExample(example);
        for (Supplier supplier1 : supplierList) {
            if (supplier1.getSupplier().equals(supplier.getSupplier())) {//判断名称是否已存在
                result.setErr(ErrorCode.NAME_AND_EXIST);
                return;
            }
        }
        supplierDao.insertSelective(supplier);
        result.setResultStatus(true);
    }

    /**
     * @param supplierId 供应商id值
     * @param result     结果说明
     */
    @Transactional
    public void deleteSupplierInfo(long supplierId, GeneralResult result)
    {
        Supplier supplier = new Supplier();
        supplier.setId(supplierId);
        supplier.setIsDelete(1);
        supplierDao.updateByPrimaryKeySelective(supplier);
        result.setResultStatus(true);
    }

    /**
     * 根据id修改供应商信息
     *
     * @param supplier 供应商修改对象信息
     * @param result   结果说明
     */
    @Transactional
    public void updateSupplierInfo(Supplier supplier, GeneralResult result)
    {
        //判断用户填写名字是否已存在！
        {
            Example example = new Example(Supplier.class);
            example.createCriteria().andEqualTo("isDelete", 0);
            List<Supplier> supplierList = supplierDao.selectByExample(example);
            for (Supplier supplier1 : supplierList) {
                if (supplier1.getSupplier().equals(supplier.getSupplier())) {
                    result.setErr(ErrorCode.NAME_AND_EXIST);
                    return;
                }
            }
            supplierDao.updateByPrimaryKeySelective(supplier);
            result.setResultStatus(true);
        }
    }


}//类结尾标识
