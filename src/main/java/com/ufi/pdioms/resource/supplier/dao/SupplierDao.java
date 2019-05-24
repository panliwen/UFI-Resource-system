package com.ufi.pdioms.resource.supplier.dao;

import com.ufi.pdioms.resource.supplier.model.Supplier;
import tk.mybatis.mapper.common.Mapper;

/**
 * 配件dao层接口
 */
@org.apache.ibatis.annotations.Mapper
public interface SupplierDao extends Mapper<Supplier>
{

    Integer findSupplierCount(String search);

    Integer findContactsCount(String search);
}
