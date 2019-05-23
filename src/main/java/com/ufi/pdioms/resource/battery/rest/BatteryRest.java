package com.ufi.pdioms.resource.battery.rest;

import com.ufi.pdioms.resource.battery.model.Battery;
import com.ufi.pdioms.resource.battery.service.BatteryService;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.utlis.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 电池映射接口
 */
@RestController
@RequestMapping("/resource")
public class BatteryRest
{
    private static final Logger LOGGER= LoggerFactory.getLogger(BatteryRest.class);

    @Autowired
    private BatteryService batteryService;

    //新增电池信息录入
    @PostMapping("/battery/add")
    public GeneralResult addBatteryInfo(@Valid @RequestBody Battery battery)
    {
        LOGGER.info("new battery info param:{}", JsonUtils.obj2json(battery));
        GeneralResult result= new GeneralResult();
        batteryService.addBatteryInfo(battery,result);
       return result;
    }

    //根据id删除电池信息
    @DeleteMapping("/battery/{batteryId}")
    public GeneralResult deleteBatteryInfo(@PathVariable("batteryId") long batteryId)
    {
        GeneralResult result = new GeneralResult();
        batteryService.deleteBatteryInfo(batteryId, result);
        return result;
    }

    //根据id修改电池信息
    @PutMapping("/battery/{batteryId}/edit")
    public GeneralResult updateBatteryInfo(@Valid @RequestBody Battery battery,
                                           @PathVariable("batteryId") long batteryId
                                            )
    {
        LOGGER.info("update battery info param:{}", JsonUtils.obj2json(battery));
        GeneralResult result = new GeneralResult();
        battery.setId(batteryId);
        batteryService.updateBatteryInfo(battery, result);
        return result;
    }

    //获得电池信息列表
    @GetMapping("/battery")
    public GeneralResult getBatteryInfo(  @RequestParam(value="pageNo", required=false, defaultValue="1") Integer pageNo,
                                           @RequestParam(value="pageSize", required=false, defaultValue="20") Integer pageSize,
                                           @RequestParam(value="model", required=false, defaultValue="") String model,
                                           @RequestParam(value="search", required=false, defaultValue="") String search,
                                           @RequestParam(value="status", required=false, defaultValue="") Integer status,
                                           @RequestParam(value="manufacturer", required=false, defaultValue="") String manufacturer,
                                           @RequestParam(value="supplier", required=false, defaultValue="") String supplier
                                            )
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(batteryService.getBatteryInfo(pageNo, pageSize, model,search,status,manufacturer,supplier));
        return result;
    }

    //使用电池Id查询电池详细详情
    @GetMapping("/battery/{batteryId}")
    public GeneralResult getBatteryDetails(@PathVariable(value = "batteryId") Long batteryId)
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(batteryService.getBatteryDetails(batteryId));
        return result;
    }

}
