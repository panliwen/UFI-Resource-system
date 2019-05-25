package com.ufi.pdioms.resource.model.service;

import com.ufi.pdioms.resource.model.model.Model;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.common.model.PageResult;


/**
 * 设备型号业务层接口
 */
public interface ModelService
{
    /**
     *获得设备型号所有列表信息
     * @param pageNo  分页起始页
     * @param pageSize  分页页大小
     * @param search  条件搜索的设备型号型号
     * @return  返回搜索结果集
     */
    PageResult getModelInfo(Integer pageNo, Integer pageSize,String search,String manufacturer,String category);

    /**
     * 新增设备型号信息
     * @param model  设备型号对象信息
     * @param result  结果说明
     */
    void addModelInfo(Model model, GeneralResult result);

    /**
     *
     * @param modelId  设备型号id值
     * @param result  结果说明
     */
    void deleteModelInfo(long modelId, GeneralResult result);

    /**
     * 根据id修改设备型号信息
     * @param model  设备型号修改对象信息
     * @param result  结果说明
     */
    void updateModelInfo(Model model, GeneralResult result);

    /**
     * 使用设备型号Id查询设备型号详细详情信息
     * @param modelId  设备型号Id值
     * @return  返回查询结果集
     */
    Model getModelDetails(Long modelId);
}
