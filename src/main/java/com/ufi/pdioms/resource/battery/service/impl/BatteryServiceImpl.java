package com.ufi.pdioms.resource.battery.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ufi.pdioms.resource.battery.dao.BatteryDao;
import com.ufi.pdioms.resource.battery.model.Battery;
import com.ufi.pdioms.resource.battery.service.BatteryService;
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
 * 电池业务层实现类
 */
@Service
public class BatteryServiceImpl implements BatteryService
{

    @Autowired
    private BatteryDao batteryDao;

    /**
     * 获得电池所有列表信息
     *
     * @param pageNo       分页起始页
     * @param pageSize     分页页大小
     * @param model 条件搜索的电池型号
     * @return 返回搜索结果集
     */
    @Override
    public PageResult getBatteryInfo(Integer pageNo, Integer pageSize, String model,String search,Integer status,String manufacturer,String supplier)
    {
        PageHelper.startPage(pageNo,pageSize); //分页参数设置
        //判断搜索参数不等于空或者不等于空字符
        if (StringUtil.isEmpty(model) & StringUtil.isEmpty(search) & StringUtil.isEmpty(manufacturer) & StringUtil.isEmpty(supplier) & status ==null) {
            Example example = new Example(Battery.class);
            example.createCriteria().andEqualTo("isDelete",0);
            List<Battery> batterys = batteryDao.selectByExample(example);
            PageInfo<Battery> pageInfo = new PageInfo<>(batterys);
            PageResult result = new PageResult(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages(), pageInfo.getSize(), pageInfo.getPageNum());
            return result;
        }
        //电池搜索条件查询《不》等于空的情况下，进行搜索分页查询
        Example example = new Example(Battery.class);
        Example.Criteria criteria = example.createCriteria();
        if(!model.equals("")) criteria.andEqualTo("model",model);
//       if(!search.equals("")) example.createCriteria().e("model",model);   这里缺补的是，搜索框中的判断那个对象的
        if(!supplier.equals(""))criteria.andEqualTo("supplier",supplier);
        if(!manufacturer.equals("")) criteria.andEqualTo("manufacturer",manufacturer);
        if(status != null) criteria.andEqualTo("status",status);

        criteria.andEqualTo("isDelete",0);
        List<Battery> batterys = batteryDao.selectByExample(example);
        PageInfo<Battery> pageInfo = new PageInfo<>(batterys);
        PageResult result = new PageResult(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages(), pageInfo.getSize(), pageInfo.getPageNum());
        return result;
    }

    /**
     * 新增电池信息
     *
     * @param battery 电池对象信息
     * @param result  结果说明
     */
    @Transactional
    public void addBatteryInfo(Battery battery, GeneralResult result)
    {
       
        batteryDao.insert(battery);
        result.setResultStatus(true);
    }

    /**
     * @param batteryId 电池id值
     * @param result   结果说明
     */
    @Transactional
    public void deleteBatteryInfo(long batteryId, GeneralResult result)
    {
        Battery battery= new Battery();
        battery.setId(batteryId);
        battery.setIsDelete(1);
        batteryDao.updateByPrimaryKeySelective(battery);
        result.setResultStatus(true);
    }

    /**
     * 根据id修改电池信息
     *
     * @param battery 电池修改对象信息
     * @param result  结果说明
     */
    @Transactional
    public void updateBatteryInfo(Battery battery, GeneralResult result)
    {
        //判断用户填写名字是否已存在！
        Example example = new Example(Battery.class);
        example.createCriteria().andEqualTo("isDelete",0);
        List<Battery> batterys = batteryDao.selectByExample(example);
        for (Battery battery1 : batterys) {
            if (battery.getSn().equals(battery1.getSn())){
                result.setErr(ErrorCode.DEVICE_SN_NUMBER_EXIST);
                return;
            }
        }
        batteryDao.updateByPrimaryKeySelective(battery);
       result.setResultStatus(true);
    }

    /**
     * 使用电池Id查询电池详细详情信息
     *
     * @param batteryId 电池Id值
     * @return 返回查询结果集
     */
    @Override
    public Battery getBatteryDetails(Long batteryId) {
        return batteryDao.selectByPrimaryKey(batteryId);
    }
}
