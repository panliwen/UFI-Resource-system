package com.ufi.pdioms.resource.parts.service;

import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.common.model.PageResult;
import com.ufi.pdioms.resource.parts.model.Parts;


/**
 * 配件业务层接口
 */
public interface PartsService
{
    /**
     *获得配件所有列表信息
     * @param pageNo  分页起始页
     * @param pageSize  分页页大小
     * @param model  条件搜索的配件型号
     * @return  返回搜索结果集
     */
    PageResult getPartsInfo(Integer pageNo, Integer pageSize, String model, String search, Integer status, String manufacturer, String supplier);

    /**
     * 新增配件信息
     * @param parts  配件对象信息
     * @param result  结果说明
     */
    void addPartsInfo(Parts parts, GeneralResult result);

    /**
     *
     * @param partsId  配件id值
     * @param result  结果说明
     */
    void deletePartsInfo(long partsId, GeneralResult result);

    /**
     * 根据id修改配件信息
     * @param parts  配件修改对象信息
     * @param result  结果说明
     */
    void updatePartsInfo(Parts parts, GeneralResult result);

    /**
     * 使用配件Id查询配件详细详情信息
     * @param partsId  配件Id值
     * @return  返回查询结果集
     */
    Parts getPartsDetails(Long partsId);
}
