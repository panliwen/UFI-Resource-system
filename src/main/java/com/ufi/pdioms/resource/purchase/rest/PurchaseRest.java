package com.ufi.pdioms.resource.purchase.rest;

import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.purchase.model.Purchase;
import com.ufi.pdioms.resource.purchase.service.PurchaseService;
import com.ufi.pdioms.resource.utlis.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 采购记录映射接口
 */
@RestController
@RequestMapping("/resource")
public class PurchaseRest
{
    private static final Logger LOGGER= LoggerFactory.getLogger(PurchaseRest.class);

    @Autowired
    private PurchaseService purchaseService;

    //新增采购记录信息录入
    @PostMapping("/purchase/add")
    public GeneralResult addPurchaseInfo(@Valid @RequestBody Purchase purchase)
    {
        LOGGER.info("new purchase info param:{}", JsonUtils.obj2json(purchase));
        GeneralResult result= new GeneralResult();
        purchaseService.addPurchaseInfo(purchase,result);
       return result;
    }

    //根据id删除采购记录信息
    @DeleteMapping("/purchase/{purchaseId}")
    public GeneralResult deletePurchaseInfo(@PathVariable("purchaseId") long purchaseId)
    {
        GeneralResult result = new GeneralResult();
        purchaseService.deletePurchaseInfo(purchaseId, result);
        return result;
    }

    //根据id修改采购记录信息
    @PutMapping("/purchase/{purchaseId}/edit")
    public GeneralResult updatePurchaseInfo(@Valid @RequestBody Purchase purchase,
                                           @PathVariable("purchaseId") long purchaseId
                                            )
    {
        LOGGER.info("update purchase info param:{}", JsonUtils.obj2json(purchase));
        GeneralResult result = new GeneralResult();
        purchase.setId(purchaseId);
        purchaseService.updatePurchaseInfo(purchase, result);
        return result;
    }

    //获得采购记录信息列表
    @GetMapping("/purchase")
    public GeneralResult getPurchaseInfo(  @RequestParam(value="pageNo", required=false, defaultValue="1") Integer pageNo,
                                           @RequestParam(value="pageSize", required=false, defaultValue="20") Integer pageSize,
                                           @RequestParam(value="search", required=false, defaultValue="") String search
                                            )
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(purchaseService.getPurchaseInfo(pageNo, pageSize,search));
        return result;
    }

    //使用采购记录Id查询采购记录详细详情
    @GetMapping("/purchase/{purchaseId}")
    public GeneralResult getPurchaseDetails(@PathVariable(value = "purchaseId") Long purchaseId)
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(purchaseService.getPurchaseDetails(purchaseId));
        return result;
    }

}
