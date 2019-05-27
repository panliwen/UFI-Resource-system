package com.ufi.pdioms.resource.purchase.dao;

import com.ufi.pdioms.resource.purchase.model.Purchase;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 配件dao层接口
 */
@org.apache.ibatis.annotations.Mapper
public interface PurchaseDao extends Mapper<Purchase>
{

    Integer findPurchaseCount(String search);

    Integer findSupplierCount(String search);
    /**时间的过滤筛选数据**/
    List<Purchase> searchDataByDate(@Param("beginTime") String beginTime, @Param("endTime")String endTime);
}
