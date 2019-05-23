package com.ufi.pdioms.resource.battery.service;

import com.ufi.pdioms.resource.battery.model.Battery;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.common.model.PageResult;


/**
 * 电池业务层接口
 */
public interface BatteryService
{
    /**
     *获得电池所有列表信息
     * @param pageNo  分页起始页
     * @param pageSize  分页页大小
     * @param model  条件搜索的电池型号
     * @return  返回搜索结果集
     */
    PageResult getBatteryInfo(Integer pageNo, Integer pageSize, String model,String search,Integer status,String manufacturer,String supplier);

    /**
     * 新增电池信息
     * @param battery  电池对象信息
     * @param result  结果说明
     */
    void addBatteryInfo(Battery battery, GeneralResult result);

    /**
     *
     * @param batteryId  电池id值
     * @param result  结果说明
     */
    void deleteBatteryInfo(long batteryId, GeneralResult result);

    /**
     * 根据id修改电池信息
     * @param battery  电池修改对象信息
     * @param result  结果说明
     */
    void updateBatteryInfo(Battery battery, GeneralResult result);

    /**
     * 使用电池Id查询电池详细详情信息
     * @param batteryId  电池Id值
     * @return  返回查询结果集
     */
    Battery getBatteryDetails(Long batteryId);
}
