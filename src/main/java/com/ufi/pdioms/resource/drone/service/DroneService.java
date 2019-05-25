package com.ufi.pdioms.resource.drone.service;

import com.ufi.pdioms.resource.drone.model.Drone;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.common.model.PageResult;


/**
 * 无人机业务层接口
 */
public interface DroneService
{
    /**
     *获得无人机所有列表信息
     * @param pageNo  分页起始页
     * @param pageSize  分页页大小
     * @param model  条件搜索的无人机型号
     * @return  返回搜索结果集
     */
    PageResult getDroneInfo(Integer pageNo, Integer pageSize, String model, String search, Integer status, String manufacturer, String supplier);

    /**
     * 新增无人机信息
     * @param drone  无人机对象信息
     * @param result  结果说明
     */
    void addDroneInfo(Drone drone, GeneralResult result);

    /**
     *
     * @param droneId  无人机id值
     * @param result  结果说明
     */
    void deleteDroneInfo(long droneId, GeneralResult result);

    /**
     * 根据id修改无人机信息
     * @param drone  无人机修改对象信息
     * @param result  结果说明
     */
    void updateDroneInfo(Drone drone, GeneralResult result);

    /**
     * 使用无人机Id查询无人机详细详情信息
     * @param droneId  无人机Id值
     * @return  返回查询结果集
     */
    Drone getDroneDetails(Long droneId);
}
