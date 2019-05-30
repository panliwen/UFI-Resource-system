package com.ufi.pdioms.resource.drone.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ufi.pdioms.resource.common.model.ErrorCode;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.common.model.PageResult;
import com.ufi.pdioms.resource.drone.dao.DroneDao;
import com.ufi.pdioms.resource.drone.model.Drone;
import com.ufi.pdioms.resource.drone.service.DroneService;
import com.ufi.pdioms.resource.model.dao.ModelDao;
import com.ufi.pdioms.resource.model.model.Model;
import com.ufi.pdioms.resource.purchase.dao.PurchaseDao;
import com.ufi.pdioms.resource.purchase.dao.PurchaseDetailsDao;
import com.ufi.pdioms.resource.purchase.model.Purchase;
import com.ufi.pdioms.resource.purchase.model.PurchaseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * 设备无人机业务层实现类
 */
@Service
public class DroneServiceImpl implements DroneService
{
    @Autowired
    private DroneDao droneDao; //设备无人机
    @Autowired
    private ModelDao modelDao; //设备型号
    @Autowired
    private PurchaseDao purchaseDao; //采购信息
    @Autowired
    private PurchaseDetailsDao purchaseDetailsDao; //采购详情信息

    /**
     *     获得无人机所有列表信息
     * @param pageNo  分页起始页
     * @param pageSize  分页页大小
     * @param model  条件搜索的无人机型号
     * @param status  无人机状态
     * @param manufacturer 厂家名称
     * @param supplier  供应商名称
     * @return  返回搜索结果集
     */
    @Override
    public PageResult getDroneInfo(Integer pageNo, Integer pageSize, String model,String search,Integer status,String manufacturer,String supplier)
    {
        PageHelper.startPage(pageNo,pageSize); //分页参数设置
        //判断搜索参数不等于空或者不等于空字符
        if (StringUtil.isEmpty(model) & StringUtil.isEmpty(search) & StringUtil.isEmpty(manufacturer) & StringUtil.isEmpty(supplier) & status ==null) {
            Example example = new Example(Drone.class);
            example.createCriteria().andEqualTo("isDelete",0);
            List<Drone> drones = droneDao.selectByExample(example);
            PageInfo<Drone> pageInfo = new PageInfo<>(drones);
            PageResult result = new PageResult(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
            return result;
        }
        //设备无人机搜索条件查询《不》等于空的情况下，进行搜索分页查询
        Example example = new Example(Drone.class);
        Example.Criteria criteria = example.createCriteria();
        if(!model.equals("")) criteria.andEqualTo("model",model);
        if(!search.equals("")) criteria.andEqualTo(searchIf(search),search);  //搜索框中的内容进行搜索的条件筛选
        if(!supplier.equals(""))criteria.andEqualTo("supplier",supplier);
        if(!manufacturer.equals("")) criteria.andEqualTo("manufacturer",manufacturer);
        if(status != null) criteria.andEqualTo("status",status);
        criteria.andEqualTo("isDelete",0);

        List<Drone> drones = droneDao.selectByExample(example);
        PageInfo<Drone> pageInfo = new PageInfo<>(drones);
        PageResult result = new PageResult(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }
    /****这个是判断搜索中是对应那个字段进行条件筛选的****/
    private String searchIf(String search)
    {
        int number = droneDao.findNumberCount(search);
        int sn = droneDao.findSnCount(search);
        if (number >= 1) return "number";
        if (sn >= 1) {
            return "sn";
        }else {
            return " ";
        }
    }

    /**
     * 新增设备无人机信息
     *
     * @param drone 设备无人机对象信息
     * @param result  结果说明
     */
    @Transactional
    public void addDroneInfo(Drone drone, GeneralResult result)
    {
       //通过型号查询厂家信息
        List<Model> modelList = modelDao.selectAll();
        for (Model model : modelList)
        {
            if (model.getModel().equals(drone.getModel())) drone.setManufacturer(model.getManufacturer());
        }
        droneDao.insertSelective(drone); //插入无人机信息
        purchaseDao.insertSelective(drone.getPurchase());//插入采购数据信息

        //采购详情的信息
        PurchaseDetails purchaseDetails= new PurchaseDetails();
        purchaseDetails.setParentId(drone.getPurchase().getId()); //采购信息id作为详情信息父id值
        purchaseDetails.setNumber(drone.getNumber());
        purchaseDetails.setModel(drone.getModel());
        purchaseDetails.setCategory("无人机");
        purchaseDetails.setSn(drone.getSn());

        purchaseDetailsDao.insertSelective(purchaseDetails); //插入采购详情信息
        result.setResultStatus(true);
    }

    /**
     * @param droneId 设备无人机id值
     * @param result   结果说明
     */
    @Transactional
    public void deleteDroneInfo(long droneId, GeneralResult result)
    {
        Drone drone= new Drone();
        drone.setId(droneId);
        drone.setIsDelete(1);
        droneDao.updateByPrimaryKeySelective(drone);
        result.setResultStatus(true);
    }

    /**
     * 根据id修改设备无人机信息
     *
     * @param drone 设备无人机修改对象信息
     * @param result  结果说明
     */
    @Transactional
    public void updateDroneInfo(Drone drone, GeneralResult result)
    {
        //判断用户填写名字是否已存在！
        Example example = new Example(Drone.class);
        example.createCriteria().andEqualTo("isDelete",0);
        List<Drone> drones = droneDao.selectByExample(example);
        for (Drone drone1 : drones) {
            if (drone.getSn().equals(drone1.getSn())){
                result.setErr(ErrorCode.DEVICE_SN_NUMBER_EXIST);
                return;
            }
        }
        droneDao.updateByPrimaryKeySelective(drone);
       result.setResultStatus(true);
    }

    /**
     * 使用设备无人机Id查询设备无人机详细详情信息
     *
     * @param droneId 设备无人机Id值
     * @return 返回查询结果集
     */
    @Override
    public Drone getDroneDetails(Long droneId)
    {
        Drone drone = droneDao.selectByPrimaryKey(droneId); //设备无人机信息
        Example example = new Example(PurchaseDetails.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sn",drone.getSn());
        List<PurchaseDetails> purchaseDetails =  purchaseDetailsDao.selectByExample(example); //获取采购详情中信息，拿父if值
        long getId=0;
        for (PurchaseDetails purchaseDetail : purchaseDetails) {
            getId = purchaseDetail.getParentId();
        }
        Purchase purchase = purchaseDao.selectByPrimaryKey(getId); //获得采购记录信息
        drone.setPurchase(purchase);
        return drone ;
    }
}
