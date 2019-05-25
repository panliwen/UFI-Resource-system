package com.ufi.pdioms.resource.drone.rest;

import com.ufi.pdioms.resource.drone.service.DroneService;
import com.ufi.pdioms.resource.common.model.GeneralResult;
import com.ufi.pdioms.resource.drone.model.Drone;
import com.ufi.pdioms.resource.utlis.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 无人机映射接口
 */
@RestController
@RequestMapping("/resource")
public class DroneRest
{
    private static final Logger LOGGER= LoggerFactory.getLogger(DroneRest.class);

    @Autowired
    private DroneService droneService;

    //新增无人机信息录入
    @PostMapping("/drone/add")
    public GeneralResult addDroneInfo(@Valid @RequestBody Drone drone)
    {
        LOGGER.info("new drone info param:{}", JsonUtils.obj2json(drone));
        GeneralResult result= new GeneralResult();
        droneService.addDroneInfo(drone,result);
       return result;
    }

    //根据id删除无人机信息
    @DeleteMapping("/drone/{droneId}")
    public GeneralResult deleteDroneInfo(@PathVariable("droneId") long droneId)
    {
        GeneralResult result = new GeneralResult();
        droneService.deleteDroneInfo(droneId, result);
        return result;
    }

    //根据id修改无人机信息
    @PutMapping("/drone/{droneId}/edit")
    public GeneralResult updateDroneInfo(@Valid @RequestBody Drone drone,
                                           @PathVariable("droneId") long droneId
                                            )
    {
        LOGGER.info("update drone info param:{}", JsonUtils.obj2json(drone));
        GeneralResult result = new GeneralResult();
        drone.setId(droneId);
        droneService.updateDroneInfo(drone, result);
        return result;
    }

    //获得无人机信息列表
    @GetMapping("/drone")
    public GeneralResult getDroneInfo(  @RequestParam(value="pageNo", required=false, defaultValue="1") Integer pageNo,
                                           @RequestParam(value="pageSize", required=false, defaultValue="20") Integer pageSize,
                                           @RequestParam(value="model", required=false, defaultValue="") String model,
                                           @RequestParam(value="search", required=false, defaultValue="") String search,
                                           @RequestParam(value="status", required=false, defaultValue="") Integer status,
                                           @RequestParam(value="manufacturer", required=false, defaultValue="") String manufacturer,
                                           @RequestParam(value="supplier", required=false, defaultValue="") String supplier
                                            )
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(droneService.getDroneInfo(pageNo, pageSize, model,search,status,manufacturer,supplier));
        return result;
    }

    //使用无人机Id查询无人机详细详情
    @GetMapping("/drone/{droneId}")
    public GeneralResult getDroneDetails(@PathVariable(value = "droneId") Long droneId)
    {
        GeneralResult result = new GeneralResult();
        result.setResultData(droneService.getDroneDetails(droneId));
        return result;
    }

}
