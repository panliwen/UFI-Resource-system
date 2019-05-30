package com.ufi.pdioms.resource.parts.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ufi.pdioms.resource.common.model.ErrorCode;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.common.model.PageResult;
import com.ufi.pdioms.resource.model.dao.ModelDao;
import com.ufi.pdioms.resource.model.model.Model;
import com.ufi.pdioms.resource.parts.dao.PartsDao;
import com.ufi.pdioms.resource.parts.model.Parts;
import com.ufi.pdioms.resource.parts.service.PartsService;
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
 * 设备配件业务层实现类
 */
@Service
public class PartsServiceImpl implements PartsService
{

    @Autowired
    private PartsDao partsDao;
    @Autowired
    private ModelDao modelDao; //设备型号
    @Autowired
    private PurchaseDao purchaseDao; //采购信息
    @Autowired
    private PurchaseDetailsDao purchaseDetailsDao; //采购详情信息


    /**
     * 获得设备配件所有列表信息
     *
     * @param pageNo       分页起始页
     * @param pageSize     分页页大小
     * @param model 条件搜索的配件型号
     * @return 返回搜索结果集
     */
    @Override
    public PageResult getPartsInfo(Integer pageNo, Integer pageSize, String model,String search,Integer status,String manufacturer,String supplier)
    {
        PageHelper.startPage(pageNo,pageSize); //分页参数设置
        //判断搜索参数不等于空或者不等于空字符
        if (StringUtil.isEmpty(model) & StringUtil.isEmpty(search) & StringUtil.isEmpty(manufacturer) & StringUtil.isEmpty(supplier) & status ==null) {
            Example example = new Example(Parts.class);
            example.createCriteria().andEqualTo("isDelete",0);
            List<Parts> partss = partsDao.selectByExample(example);
            PageInfo<Parts> pageInfo = new PageInfo<>(partss);
            PageResult result = new PageResult(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
            return result;
        }
        //配件搜索条件查询《不》等于空的情况下，进行搜索分页查询
        Example example = new Example(Parts.class);
        Example.Criteria criteria = example.createCriteria();
        if(!model.equals("")) criteria.andEqualTo("model",model);
        if(!search.equals("")) criteria.andEqualTo(searchIf(search),search);  //搜索框中的内容进行搜索的条件筛选
        if(!supplier.equals(""))criteria.andEqualTo("supplier",supplier);
        if(!manufacturer.equals("")) criteria.andEqualTo("manufacturer",manufacturer);
        if(status != null) criteria.andEqualTo("status",status);
        criteria.andEqualTo("isDelete",0);

        List<Parts> partss = partsDao.selectByExample(example);
        PageInfo<Parts> pageInfo = new PageInfo<>(partss);
        PageResult result = new PageResult(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }
    /****这个是判断搜索中是对应那个字段进行条件筛选的****/
    private String searchIf(String search)
    {
        int number = partsDao.findNumberCount(search);
        int sn = partsDao.findSnCount(search);
        if (number >= 1) return "number";
        if (sn >= 1) {
            return "sn";
        }else {
            return " ";
        }
    }

    /**
     * 新增配件信息
     *
     * @param parts 配件对象信息
     * @param result  结果说明
     */
    @Transactional
    public void addPartsInfo(Parts parts, GeneralResult result)
    {
        //通过型号查询厂家信息
        List<Model> modelList = modelDao.selectAll();
        for (Model model : modelList)
        {
            if (model.getModel().equals(parts.getModel())) parts.setManufacturer(model.getManufacturer());
        }
        partsDao.insertSelective(parts); //插入配件数据信息
        purchaseDao.insertSelective(parts.getPurchase());//插入采购数据信息

        //采购详情的信息
        PurchaseDetails purchaseDetails= new PurchaseDetails();
        purchaseDetails.setParentId(parts.getPurchase().getId()); //采购信息id作为详情信息父id值
        purchaseDetails.setNumber(parts.getNumber());
        purchaseDetails.setModel(parts.getModel());
        purchaseDetails.setCategory("配件");
        purchaseDetails.setSn(parts.getSn());

        purchaseDetailsDao.insertSelective(purchaseDetails);

        result.setResultStatus(true);
    }

    /**
     * @param partsId 配件id值
     * @param result   结果说明
     */
    @Transactional
    public void deletePartsInfo(long partsId, GeneralResult result)
    {
        Parts parts= new Parts();
        parts.setId(partsId);
        parts.setIsDelete(1);
        partsDao.updateByPrimaryKeySelective(parts);
        result.setResultStatus(true);
    }

    /**
     * 根据id修改配件信息
     *
     * @param parts 配件修改对象信息
     * @param result  结果说明
     */
    @Transactional
    public void updatePartsInfo(Parts parts, GeneralResult result)
    {
        //判断用户填写名字是否已存在！
        Example example = new Example(Parts.class);
        example.createCriteria().andEqualTo("isDelete",0);
        List<Parts> partss = partsDao.selectByExample(example);
        for (Parts parts1 : partss) {
            if (parts.getSn().equals(parts1.getSn())){
                result.setErr(ErrorCode.DEVICE_SN_NUMBER_EXIST);
                return;
            }
        }
        partsDao.updateByPrimaryKeySelective(parts);
       result.setResultStatus(true);
    }

    /**
     * 使用配件Id查询配件详细详情信息
     *
     * @param partsId 配件Id值
     * @return 返回查询结果集
     */
    @Override
    public Parts getPartsDetails(Long partsId)
    {
        Parts parts = partsDao.selectByPrimaryKey(partsId); //设备无人机信息
        Example example = new Example(PurchaseDetails.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sn",parts.getSn());
        List<PurchaseDetails> purchaseDetails =  purchaseDetailsDao.selectByExample(example); //获取采购详情中信息，拿父if值
        long getId=0;
        for (PurchaseDetails purchaseDetail : purchaseDetails) {
            getId = purchaseDetail.getParentId();
        }
        Purchase purchase = purchaseDao.selectByPrimaryKey(getId); //获得采购记录信息
        parts.setPurchase(purchase);
        return parts ;
    }
}
