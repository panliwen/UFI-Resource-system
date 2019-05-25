package com.ufi.pdioms.resource.drone.dao;

import com.ufi.pdioms.resource.drone.model.Drone;
import tk.mybatis.mapper.common.Mapper;

/**
 * 设备无人机dao层接口
 */
@org.apache.ibatis.annotations.Mapper
public interface DroneDao extends Mapper<Drone>
{

    Integer findNumberCount(String search);

    Integer findSnCount(String search);
}
