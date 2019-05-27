package com.ufi.pdioms.resource.purchase.service;

import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.common.model.PageResult;
import com.ufi.pdioms.resource.purchase.model.Purchase;


/**
 * 采购记录业务层接口
 */
public interface PurchaseService
{
    /**
     *获得采购记录所有列表信息
     * @param pageNo  分页起始页
     * @param pageSize  分页页大小
     * @param search  条件搜索的采购记录型号
     * @return  返回搜索结果集
     */
    PageResult getPurchaseInfo(Integer pageNo, Integer pageSize, String search, String beginTime,String endTime);

    /**
     * 新增采购记录信息
     * @param purchase  采购记录对象信息
     * @param result  结果说明
     */
    void addPurchaseInfo(Purchase purchase, GeneralResult result);

    /**
     *
     * @param purchaseId  采购记录id值
     * @param result  结果说明
     */
    void deletePurchaseInfo(long purchaseId, GeneralResult result);

    /**
     * 根据id修改采购记录信息
     * @param purchase  采购记录修改对象信息
     * @param result  结果说明
     */
    void updatePurchaseInfo(Purchase purchase, GeneralResult result);

    /**
     * 使用采购记录Id查询采购记录详细详情信息
     * @param purchaseId  采购记录Id值
     * @return  返回查询结果集
     */
    Purchase getPurchaseDetails(Long purchaseId);
}
