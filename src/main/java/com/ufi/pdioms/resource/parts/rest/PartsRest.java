package com.ufi.pdioms.resource.parts.rest;

import com.ufi.pdioms.resource.parts.service.PartsService;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.parts.model.Parts;
import com.ufi.pdioms.resource.utlis.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 配件映射接口
 */
@RestController
@RequestMapping("/resource/mgt")
public class PartsRest
{
    private static final Logger LOGGER= LoggerFactory.getLogger(PartsRest.class);

    @Autowired
    private PartsService partsService;

    //新增配件信息录入
    @PostMapping("/parts")
    public GeneralResult addPartsInfo(@Valid @RequestBody Parts parts)
    {
        LOGGER.info("new parts info param:{}", JsonUtils.obj2json(parts));
        GeneralResult result= new GeneralResult();
        partsService.addPartsInfo(parts,result);
       return result;
    }

    //根据id删除配件信息
    @DeleteMapping("/parts/{partsId}")
    public GeneralResult deletePartsInfo(@PathVariable("partsId") long partsId)
    {
        GeneralResult result = new GeneralResult();
        partsService.deletePartsInfo(partsId, result);
        return result;
    }

    //根据id修改配件信息
    @PutMapping("/parts/{partsId}/edit")
    public GeneralResult updatePartsInfo(@Valid @RequestBody Parts parts,
                                           @PathVariable("partsId") long partsId
                                            )
    {
        LOGGER.info("update parts info param:{}", JsonUtils.obj2json(parts));
        GeneralResult result = new GeneralResult();
        parts.setId(partsId);
        partsService.updatePartsInfo(parts, result);
        return result;
    }

    //获得配件信息列表
    @GetMapping("/parts")
    public GeneralResult getPartsInfo(  @RequestParam(value="pageNo", required=false, defaultValue="1") Integer pageNo,
                                           @RequestParam(value="pageSize", required=false, defaultValue="20") Integer pageSize,
                                           @RequestParam(value="model", required=false, defaultValue="") String model,
                                           @RequestParam(value="search", required=false, defaultValue="") String search,
                                           @RequestParam(value="status", required=false, defaultValue="") Integer status,
                                           @RequestParam(value="manufacturer", required=false, defaultValue="") String manufacturer,
                                           @RequestParam(value="supplier", required=false, defaultValue="") String supplier
                                            )
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(partsService.getPartsInfo(pageNo, pageSize, model,search,status,manufacturer,supplier));
        return result;
    }

    //使用配件Id查询配件详细详情
    @GetMapping("/parts/{partsId}")
    public GeneralResult getPartsDetails(@PathVariable(value = "partsId") Long partsId)
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(partsService.getPartsDetails(partsId));
        return result;
    }

}
