package com.ufi.pdioms.resource.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.common.model.PageResult;
import com.ufi.pdioms.resource.purchase.dao.PurchaseDao;
import com.ufi.pdioms.resource.purchase.dao.PurchaseDetailsDao;
import com.ufi.pdioms.resource.purchase.model.Purchase;
import com.ufi.pdioms.resource.purchase.model.PurchaseDetails;
import com.ufi.pdioms.resource.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * 采购记录业务层实现类
 */
@Service
public class PurchaseServiceImpl implements PurchaseService
{

    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private PurchaseDetailsDao purchaseDetailsDao;

    /**
     * 获得采购记录所有列表信息
     *
     * @param pageNo       分页起始页
     * @param pageSize     分页页大小
     * @param search 条件搜索的采购记录型号
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 返回搜索结果集
     */
    @Override
    public PageResult getPurchaseInfo(Integer pageNo, Integer pageSize, String search, String beginTime, String endTime)
    {
        PageHelper.startPage(pageNo,pageSize); //分页参数设置
        //判断搜索参数不等于空或者不等于空字符
        if (StringUtil.isEmpty(search) & StringUtil.isEmpty(beginTime) & StringUtil.isEmpty(endTime)) {
            Example example = new Example(Purchase.class);
            example.createCriteria().andEqualTo("isDelete",0);
            List<Purchase> purchases = purchaseDao.selectByExample(example);
            PageInfo<Purchase> pageInfo = new PageInfo<>(purchases);
            PageResult result = new PageResult(pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getSize(), pageInfo.getPageNum(), pageInfo.getList());
            return result;
        }

        //采购记录搜索条件查询《不》等于空的情况下，进行搜索分页查询
        Example example = new Example(Purchase.class);
        Example.Criteria criteria = example.createCriteria();
        if(!search.equals("")) criteria.andEqualTo(searchIf(search),search);  //搜索框中的内容进行搜索的条件筛选
        criteria.andEqualTo("isDelete",0);

        List<Purchase> purchases = purchaseDao.selectByExample(example);
        PageInfo<Purchase> pageInfo = new PageInfo<>(purchases);
        PageResult result = new PageResult(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        if(!search.equals("")) return result;

        if (!beginTime.equals("") & !endTime.equals("")){ //在筛选时间不等于空的情况下，进行时间筛选搜索数据
            List<Purchase> dataByDate = findDataByDate(beginTime, endTime);
            PageInfo<Purchase> dateInfo = new PageInfo<>(dataByDate);
            PageResult result1 = new PageResult(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
            return result1;
        }
        return result;
    }

    /**
     *      按照时间日期进行筛选数据信息
     * @param beginTime  开始时间
     * @param endTime   结束时间
     * @return  返回查询结果集
     */
    private List<Purchase> findDataByDate(String beginTime, String endTime) {
        List<Purchase> purchaseList = purchaseDao.searchDataByDate(beginTime, endTime);
        return purchaseList;
    }

    /****这个是判断搜索中是对应那个字段进行条件筛选的****/
    private String searchIf(String search)
    {
        int purchase = purchaseDao.findPurchaseCount(search);
        int supplier = purchaseDao.findSupplierCount(search);
        if (purchase >= 1) return "purchase";
        if (supplier >= 1) {
            return "supplier";
        }else {
            return " ";
        }
    }

    /**
     * 新增采购记录信息
     *
     * @param purchase 采购记录对象信息
     * @param result  结果说明
     */
    @Transactional
    public void addPurchaseInfo(Purchase purchase, GeneralResult result)
    {
        purchaseDao.insertSelective(purchase);  //采购记录插入对象信息

        List<PurchaseDetails> detailsList = purchase.getDetailsList();//获取到采购记录中的集合对象数据

        Long id = purchase.getId(); //插入返回主键id值
        for (PurchaseDetails details : detailsList) {
            PurchaseDetails purchaseDetails = new PurchaseDetails();
            purchaseDetails.setParentId(id);
            purchaseDetails.setSn(details.getSn());
            purchaseDetails.setCategory(details.getCategory());
            purchaseDetails.setModel(details.getModel());
            purchaseDetails.setNumber(details.getNumber());
            purchaseDetailsDao.insertSelective(purchaseDetails);
        }
        result.setResultStatus(true);
    }

    /**
     * @param purchaseId 采购记录id值
     * @param result   结果说明
     */
    @Transactional
    public void deletePurchaseInfo(long purchaseId, GeneralResult result)
    {
        Purchase purchase= new Purchase();
        purchase.setId(purchaseId);
        purchaseDao.updateByPrimaryKeySelective(purchase);
        result.setResultStatus(true);
    }

    /**
     * 根据id修改采购记录信息
     *
     * @param purchase 采购记录修改对象信息
     * @param result  结果说明
     */
    @Transactional
    public void updatePurchaseInfo(Purchase purchase, GeneralResult result)
    {
        purchaseDao.updateByPrimaryKeySelective(purchase);
        result.setResultStatus(true);
    }

    /**
     * 使用采购记录Id查询采购记录详细详情信息
     *
     * @param purchaseId 采购记录Id值
     * @return 返回查询结果集
     */
    @Override
    public Purchase getPurchaseDetails(Long purchaseId) {

        Purchase purchase = purchaseDao.selectByPrimaryKey(purchaseId);

        Example example = new Example(PurchaseDetails.class);
        example.createCriteria().andEqualTo("parentId",purchaseId);
        List<PurchaseDetails> detailsList = purchaseDetailsDao.selectByExample(example);

       purchase.setDetailsList(detailsList);


        return purchase ;
    }
}
