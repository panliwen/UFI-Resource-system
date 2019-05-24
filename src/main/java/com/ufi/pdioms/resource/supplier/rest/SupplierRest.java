package com.ufi.pdioms.resource.supplier.rest;

import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.supplier.model.Supplier;
import com.ufi.pdioms.resource.supplier.service.SupplierService;
import com.ufi.pdioms.resource.utlis.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 供应商映射接口
 */
@RestController
@RequestMapping("/resource")
public class SupplierRest
{
    private static final Logger LOGGER= LoggerFactory.getLogger(SupplierRest.class);

    @Autowired
    private SupplierService supplierService;

    //新增供应商信息录入
    @PostMapping("/supplier/add")
    public GeneralResult addSupplierInfo(@Valid @RequestBody Supplier supplier)
    {
        LOGGER.info("new supplier info param:{}", JsonUtils.obj2json(supplier));
        GeneralResult result= new GeneralResult();
        supplierService.addSupplierInfo(supplier,result);
       return result;
    }

    //根据id删除供应商信息
    @DeleteMapping("/supplier/{supplierId}")
    public GeneralResult deleteSupplierInfo(@PathVariable("supplierId") long supplierId)
    {
        GeneralResult result = new GeneralResult();
        supplierService.deleteSupplierInfo(supplierId, result);
        return result;
    }

    //根据id修改供应商信息
    @PutMapping("/supplier/{supplierId}/edit")
    public GeneralResult updateSupplierInfo(@Valid @RequestBody Supplier supplier,
                                           @PathVariable("supplierId") long supplierId
                                            )
    {
        LOGGER.info("update supplier info param:{}", JsonUtils.obj2json(supplier));
        GeneralResult result = new GeneralResult();
        supplier.setId(supplierId);
        supplierService.updateSupplierInfo(supplier, result);
        return result;
    }

    //获得供应商信息列表
    @GetMapping("/supplier")
    public GeneralResult getSupplierInfo(  @RequestParam(value="pageNo", required=false, defaultValue="1") Integer pageNo,
                                           @RequestParam(value="pageSize", required=false, defaultValue="20") Integer pageSize,
                                           @RequestParam(value="search", required=false, defaultValue="") String search
                                            )
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(supplierService.getSupplierInfo(pageNo, pageSize,search));
        return result;
    }



}
