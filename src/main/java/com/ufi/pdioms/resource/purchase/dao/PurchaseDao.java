package com.ufi.pdioms.resource.purchase.dao;

import com.ufi.pdioms.resource.purchase.model.Purchase;
import tk.mybatis.mapper.common.Mapper;

/**
 * 配件dao层接口
 */
@org.apache.ibatis.annotations.Mapper
public interface PurchaseDao extends Mapper<Purchase>
{

    Integer findPurchaseCount(String search);

    Integer findSupplierCount(String search);
}
