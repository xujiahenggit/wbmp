package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dto.GroupDTO;
import com.bank.manage.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(tags = "设备分组接口")
@RestController
@RequestMapping("/group")
@Slf4j
public class GroupController extends BaseController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/save")
    @ApiOperation(value = "新增设备分组")
    @ApiImplicitParam(name = "groupDTO", value = "设备分组信息", required = true, paramType = "body", dataType = "GroupDTO")
    public Integer save(@RequestBody GroupDTO groupDTO, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return groupService.save(groupDTO,tokenUserInfo);
    }


    @PostMapping("/updateGroup")
    @ApiOperation(value = "修改设备分组")
    @ApiImplicitParam(name = "groupDTO", value = "素材目录信息", required = true, paramType = "body", dataType = "GroupDTO")
    public Integer updateCatalog(@RequestBody GroupDTO groupDTO){
        return groupService.updateGroup(groupDTO);
    }

    @GetMapping("/findAllGroup")
    @ApiOperation(value = "查询设备分组树形结构")
    public List<Map<String,Object>> findGroup(){
        List<Map<String,Object>> listGroup = groupService.findGroup();
        return listGroup;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除设备分组")
    @ApiImplicitParam(name = "id", value = "设备组唯一标识", required = true, paramType = "path")
    public Boolean deleteGroup(@PathVariable Integer id){
        return groupService.deleteGroup(id);
    }


    @PostMapping("/saveDeviceGroup")
    @ApiOperation(value = "添加设备到设备分组")
    @ApiImplicitParam(name = "groupId", value = "设备分组主键", dataType = "String", paramType = "query")
    public Boolean saveDeviceGroup(@RequestBody @ApiParam(value = "设备主键列表") List<Integer> deviceIdList,
                                   @RequestParam("groupId") String groupId){
        return groupService.saveDeviceGroup(deviceIdList,groupId);
    }

}
