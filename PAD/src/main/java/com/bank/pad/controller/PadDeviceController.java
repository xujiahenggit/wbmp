package com.bank.pad.controller;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dto.DeviceDTO;
import com.bank.pad.dos.TopicTypeDO;
import com.bank.pad.service.PadDeviceService;
import com.bank.pad.service.TopicTypeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/PAD")
@Api(tags = "PAD端设备列表查询")
public class PadDeviceController {

    @Autowired
    private PadDeviceService padDeviceService;

    @PostMapping("/queryDeviceList")
    @ApiOperation(value = "PAD端查询设备列表")
    @ApiImplicitParam(name = "pageQueryModel", value = "设备信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<DeviceDTO> queryList(@RequestBody PageQueryModel pageQueryModel){
        return padDeviceService.queryList(pageQueryModel);
    }

    @Resource
    private TopicTypeService topicTypeService;

    @GetMapping("/topicType")
    @ApiOperation(value = "PAD切换主题类型")
    public List<TopicTypeDO> queryList(){
        return topicTypeService.list();
    }

}
