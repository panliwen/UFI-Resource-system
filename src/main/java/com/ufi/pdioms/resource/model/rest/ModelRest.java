package com.ufi.pdioms.resource.model.rest;

import com.ufi.pdioms.resource.model.model.Model;
import com.ufi.pdioms.resource.model.service.ModelService;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.utlis.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 设备型号映射接口
 */
@RestController
@RequestMapping("/resource/mgt")
public class ModelRest
{
    private static final Logger LOGGER= LoggerFactory.getLogger(ModelRest.class);

    @Autowired
    private ModelService modelService;

    //新增设备型号信息录入
    @PostMapping("/model")
    public GeneralResult addModelInfo(@Valid @RequestBody Model model)
    {
        LOGGER.info("new model info param:{}", JsonUtils.obj2json(model));
        GeneralResult result= new GeneralResult();
        modelService.addModelInfo(model,result);
       return result;
    }

    //根据id删除设备型号信息
    @DeleteMapping("/model/{modelId}")
    public GeneralResult deleteModelInfo(@PathVariable("modelId") long modelId)
    {
        GeneralResult result = new GeneralResult();
        modelService.deleteModelInfo(modelId, result);
        return result;
    }

    //根据id修改设备型号信息
    @PutMapping("/model/{modelId}/edit")
    public GeneralResult updateModelInfo(@Valid @RequestBody Model model,
                                           @PathVariable("modelId") long modelId
                                            )
    {
        LOGGER.info("update model info param:{}", JsonUtils.obj2json(model));
        GeneralResult result = new GeneralResult();
        model.setId(modelId);
        modelService.updateModelInfo(model, result);
        return result;
    }

    //获得设备型号信息列表
    @GetMapping("/model")
    public GeneralResult getModelInfo(  @RequestParam(value="pageNo", required=false, defaultValue="1") Integer pageNo,
                                           @RequestParam(value="pageSize", required=false, defaultValue="20") Integer pageSize,
                                           @RequestParam(value="search", required=false, defaultValue="") String search,
                                           @RequestParam(value="manufacturer", required=false, defaultValue="") String manufacturer,
                                           @RequestParam(value="category", required=false, defaultValue="") String category
                                            )
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(modelService.getModelInfo(pageNo, pageSize,search,manufacturer,category));
        return result;
    }

    //使用设备型号Id查询设备型号详细详情
    @GetMapping("/model/{modelId}")
    public GeneralResult getModelDetails(@PathVariable(value = "modelId") Long modelId)
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(modelService.getModelDetails(modelId));
        return result;
    }

}
