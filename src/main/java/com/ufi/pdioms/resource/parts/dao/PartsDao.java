package com.ufi.pdioms.resource.parts.dao;

import com.ufi.pdioms.resource.parts.model.Parts;
import tk.mybatis.mapper.common.Mapper;

/**
 * 配件dao层接口
 */
@org.apache.ibatis.annotations.Mapper
public interface PartsDao extends Mapper<Parts>
{

    Integer findNumberCount(String search);

    Integer findSnCount(String search);
}
