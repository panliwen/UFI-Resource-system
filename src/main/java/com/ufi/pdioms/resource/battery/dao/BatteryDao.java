package com.ufi.pdioms.resource.battery.dao;

import com.ufi.pdioms.resource.battery.model.Battery;
import tk.mybatis.mapper.common.Mapper;

/**
 * 电池dao层接口
 */
@org.apache.ibatis.annotations.Mapper
public interface BatteryDao extends Mapper<Battery>
{

    Integer findNumberCount(String search);

    Integer findSnCount(String search);
}
