package com.ufi.pdioms.resource.manufacturer.rest;

import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.manufacturer.model.Manufacturer;
import com.ufi.pdioms.resource.manufacturer.service.ManufacturerService;
import com.ufi.pdioms.resource.utlis.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 厂家映射接口
 */
@RestController
@RequestMapping("/resource")
public class ManufacturerRest
{
    private static final Logger LOGGER= LoggerFactory.getLogger(ManufacturerRest.class);

    @Autowired
    private ManufacturerService manufacturerService;

    //新增厂家信息录入
    @PostMapping("/manufacturer/add")
    public GeneralResult addManufacturerInfo(@Valid @RequestBody Manufacturer manufacturer)
    {
        LOGGER.info("new Manufacturer info param:{}", JsonUtils.obj2json(manufacturer));
        GeneralResult result= new GeneralResult();
        manufacturerService.addManufacturerInfo(manufacturer,result);
       return result;
    }
    //根据id删除厂家信息
    @DeleteMapping("/manufacturer/{manufacturerId}")
    public GeneralResult deleteManufacturer(@PathVariable("manufacturerId") long manufacturerId)
    {
        GeneralResult result= new GeneralResult();
        manufacturerService.deleteManufacturerInfo(manufacturerId,result);
       return result;

    }
    //根据id修改厂家信息
    @PutMapping("/manufacturer/{manufacturerId}/edit")
    public GeneralResult updateManufacturerInfo(@Valid @RequestBody Manufacturer manufacturer,
                                                @PathVariable("manufacturerId") long manufacturerId
                                                )
    {
        LOGGER.info("update manufacturer info param:{}",JsonUtils.obj2json(manufacturer));
        GeneralResult result = new GeneralResult();
        manufacturer.setId(manufacturerId);
        manufacturerService.updateManufacturerInfo(manufacturer,result);
       return result;
    }
    //获得厂家信息列表
    @GetMapping("/manufacturer")
    public GeneralResult getManufacturerInfo(  @RequestParam(value="pageNo", required=false, defaultValue="1") Integer pageNo,
                                           @RequestParam(value="pageSize", required=false, defaultValue="20") Integer pageSize,
                                           @RequestParam(value="manufacturer", required=false, defaultValue="") String manufacturer
                                            )
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(manufacturerService.getManufacturerInfo(pageNo,pageSize,manufacturer));
       return result;
    }

}
