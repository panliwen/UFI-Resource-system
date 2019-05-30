package com.ufi.pdioms.resource.model.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ufi.pdioms.resource.model.dao.ModelDao;
import com.ufi.pdioms.resource.model.model.Model;
import com.ufi.pdioms.resource.model.service.ModelService;
import com.ufi.pdioms.resource.common.model.ErrorCode;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.common.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * 设备型号业务层实现类
 */
@Service
public class ModelServiceImpl implements ModelService
{

    @Autowired
    private ModelDao modelDao;

    /**
     * 获得设备型号所有列表信息
     *
     * @param pageNo       分页起始页
     * @param pageSize     分页页大小
     * @param search 条件搜索的设备型号
     * @return 返回搜索结果集
     */
    @Override
    public PageResult getModelInfo(Integer pageNo, Integer pageSize,String search,String manufacturer,String category)
    {
        PageHelper.startPage(pageNo,pageSize); //分页参数设置
        //判断搜索参数不等于空或者不等于空字符
        if ( StringUtil.isEmpty(search) & StringUtil.isEmpty(manufacturer) & StringUtil.isEmpty(category)) {
            Example example = new Example(Model.class);
            example.createCriteria().andEqualTo("isDelete",0);
            List<Model> models = modelDao.selectByExample(example);
            PageInfo<Model> pageInfo = new PageInfo<>(models);
            PageResult result = new PageResult(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
            return result;
        }
        //设备型号搜索条件查询《不》等于空的情况下，进行搜索分页查询
        Example example = new Example(Model.class);
        Example.Criteria criteria = example.createCriteria();
        if(!search.equals("")) criteria.andEqualTo("model",search);  //搜索框中的内容进行搜索的条件筛选
        if(!category.equals(""))criteria.andEqualTo("category",category);
        if(!manufacturer.equals("")) criteria.andEqualTo("manufacturer",manufacturer);
        criteria.andEqualTo("isDelete",0);

        List<Model> models = modelDao.selectByExample(example);
        PageInfo<Model> pageInfo = new PageInfo<>(models);
        PageResult result = new PageResult(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }


    /**
     * 新增设备型号信息
     *
     * @param model 设备型号对象信息
     * @param result  结果说明
     */
    @Transactional
    public void addModelInfo(Model model, GeneralResult result)
    {
        //判断用户填写型号名字是否已存在！
        Example example = new Example(Model.class);
        example.createCriteria().andEqualTo("isDelete",0);
        List<Model> models = modelDao.selectByExample(example);
        for (Model model1 : models) {
            if (model.getModel().equals(model1.getModel())){
                result.setErr(ErrorCode.DEVICE_MODEL_NAME_HAS_BEEN_USED);
                return;
            }
        }
        modelDao.insertSelective(model);
        result.setResultStatus(true);
    }

    /**
     *      删除设备型号
     * @param modelId 设备型号id值
     * @param result   结果说明
     */
    @Transactional
    public void deleteModelInfo(long modelId, GeneralResult result)
    {
        Model model= new Model();
        model.setId(modelId);
        model.setIsDelete(1);
        modelDao.updateByPrimaryKeySelective(model);
        result.setResultStatus(true);
    }

    /**
     * 根据id修改设备型号信息
     *
     * @param model 设备型号修改对象信息
     * @param result  结果说明
     */
    @Transactional
    public void updateModelInfo(Model model, GeneralResult result)
    {
        //判断用户填写名字是否已存在！
        Example example = new Example(Model.class);
        example.createCriteria().andEqualTo("isDelete",0);
        List<Model> models = modelDao.selectByExample(example);
        for (Model model1 : models) {
            if (model.getModel().equals(model1.getModel())){
                result.setErr(ErrorCode.DEVICE_MODEL_NOT_EXIST);
                return;
            }
        }
        modelDao.updateByPrimaryKeySelective(model);
       result.setResultStatus(true);
    }

    /**
     * 使用设备型号Id查询设备型号详细详情信息
     *
     * @param modelId 设备型号Id值
     * @return 返回查询结果集
     */
    @Override
    public Model getModelDetails(Long modelId) {
        return modelDao.selectByPrimaryKey(modelId);
    }
}
