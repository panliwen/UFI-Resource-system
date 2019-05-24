package com.ufi.pdioms.resource.supplier.service;

import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.common.model.PageResult;
import com.ufi.pdioms.resource.supplier.model.Supplier;


/**
 * 供应商业务层接口
 */
public interface SupplierService
{
    /**
     *获得供应商所有列表信息
     * @param pageNo  分页起始页
     * @param pageSize  分页页大小
     * @param search  条件搜索的供应商信息
     * @return  返回搜索结果集
     */
    PageResult getSupplierInfo(Integer pageNo, Integer pageSize,String search);

    /**
     * 新增供应商信息
     * @param supplier  供应商对象信息
     * @param result  结果说明
     */
    void addSupplierInfo(Supplier supplier, GeneralResult result);

    /**
     *
     * @param supplierId  供应商id值
     * @param result  结果说明
     */
    void deleteSupplierInfo(long supplierId, GeneralResult result);

    /**
     * 根据id修改供应商信息
     * @param supplier  供应商修改对象信息
     * @param result  结果说明
     */
    void updateSupplierInfo(Supplier supplier, GeneralResult result);

}
