package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dto.*;
import com.bank.manage.service.BranchService;
import com.bank.manage.service.ZhomePageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自助设备监控平台首页
 *
 * @author
 * @date 2020-07-03
 */
@Api(tags = "自助设备监控平台首页接口")
@RestController
@RequestMapping("/zhomePage")
public class ZhomePageController extends BaseController {

    @Autowired
    private ZhomePageService zhomePageService;

    @Autowired
    private BranchService branchService;

    @PostMapping("/listHomePageTermStatus")
    @ApiOperation(value = "查询设备运行状态 全部 0现金 1非现金 ")
    public List<HomePageTermStatusDTO> listHomePageTermStatus(HttpServletRequest request) {
        String branchnum = this.branchService.selectByOrgcode(getCurrentOrgcode(request));
        return this.zhomePageService.selectHomePageDeviceTerm(branchnum);
    }


    @PostMapping("/list")
    @ApiOperation(value = "首页设备运行状态查询")
    @ApiImplicitParam(name = "deviceClass", value = "现金/非现金", required = false, paramType = "query")
    public List<DeviceTermDTO> listDeviceTermDTO(@RequestParam(value = "deviceClass", required = false) String deviceClass, HttpServletRequest request) {
        String branchnum = this.branchService.selectByOrgcode(getCurrentOrgcode(request));
        List<DeviceTermDTO> deviceTermDTOS = this.zhomePageService.listDeviceTermDTO(deviceClass, branchnum);
        return deviceTermDTOS;
    }

    @PostMapping("/listSsbTransRankBar")
    @ApiOperation(value = "首页月度业务量统计 全部 现金0 非现金1")
    @ApiImplicitParam(name = "deviceClass", value = "现金/非现金", required = false, paramType = "query")
    public List<SsbTransRankBarDTO> listSsbTransRankBar(@RequestParam(value = "deviceClass", required = false) String deviceClass, HttpServletRequest request) {
        String branchnum = this.branchService.selectByOrgcode(getCurrentOrgcode(request));
        return this.zhomePageService.listSsbTransRankBar(deviceClass, branchnum);
    }

    @PostMapping("/listSsbRanking")
    @ApiOperation(value = "首页当月网点业务量排行榜 全部 现金0 非现金1")
    @ApiImplicitParam(name = "deviceClass", value = "现金/非现金", required = false, paramType = "query")
    public List<SsbRankingDTO> listSsbRanking(@RequestParam(value = "deviceClass", required = false) String deviceClass, HttpServletRequest request) {
        String branchnum = this.branchService.selectByOrgcode(getCurrentOrgcode(request));
        return this.zhomePageService.listSsbRanking(deviceClass, branchnum);
    }

    @PostMapping("/selectTransGross")
    @ApiOperation(value = "首页今日交易总量")
    public List<HomePageTransHnumDTO> selectTransGross(HttpServletRequest request) {
        String branchnum = this.branchService.selectByOrgcode(getCurrentOrgcode(request));
        List<HomePageTransHnumDTO>  homePageTransHnumDTO = this.zhomePageService.selectTransGross(branchnum);
        return homePageTransHnumDTO;
    }

    @PostMapping("/listDeviceTypeCount")
    @ApiOperation(value = "设备类型统计")
    public List<ZdeviceDictDTO> listDeviceTypeCount(HttpServletRequest request) {
        String branchnum = this.branchService.selectBranchByOrgcode(getCurrentOrgcode(request));
        List<ZdeviceDictDTO> zdeviceDictDTOS = this.zhomePageService.listDeviceTypeCount(branchnum);
        List<ZdeviceDictDTO> zdeviceDictSlaves = this.zhomePageService.listDeviceTypeCountSlave(branchnum);
        //两个list进行合并 并对connum统计数进行加和 字典表的名称规范需一致
        List<ZdeviceDictDTO> zdeviceDictList = zdeviceDictDTOSToMap(zdeviceDictDTOS, zdeviceDictSlaves);
        return zdeviceDictList;
    }

    @PostMapping("/listDevmanuCount")
    @ApiOperation(value = "设备厂商统计")
    public List<ZdeviceDictDTO> listDevmanuCount(HttpServletRequest request) {
        String branchnum = this.branchService.selectBranchByOrgcode(getCurrentOrgcode(request));
        List<ZdeviceDictDTO> zdeviceDictDTOS = this.zhomePageService.listDevmanuCount(branchnum);
        List<ZdeviceDictDTO> zdeviceDictSlaves = this.zhomePageService.listDevmanuCountSlave(branchnum);
        //两个list进行合并 并对connum统计数进行加和 字典表的名称规范需一致
        List<ZdeviceDictDTO> zdeviceDictList = zdeviceDictDTOSToMap(zdeviceDictDTOS, zdeviceDictSlaves);
        return zdeviceDictList;
    }

    //两个list进行合并 并对connum统计数进行加和
    private List<ZdeviceDictDTO> zdeviceDictDTOSToMap (List<ZdeviceDictDTO> zdeviceDictDTOS, List<ZdeviceDictDTO> zdeviceDictSlaves) {
        Map<String, Integer> map = new HashMap<>();
        //把数据放进map并对connum进行加和
        if(zdeviceDictDTOS !=null && zdeviceDictDTOS.size()>0){
            for (ZdeviceDictDTO zdeviceDictDTO : zdeviceDictDTOS) {
                map.put(zdeviceDictDTO.getDictname(),zdeviceDictDTO.getConnum());
            }
        }
        if(zdeviceDictSlaves !=null && zdeviceDictSlaves.size()>0){
            for (ZdeviceDictDTO zdeviceDictSlave: zdeviceDictSlaves) {
                if(map.containsKey(zdeviceDictSlave.getDictname())){
                    Integer integer = map.get(zdeviceDictSlave.getDictname());
                    integer = integer + zdeviceDictSlave.getConnum();
                    map.put(zdeviceDictSlave.getDictname(),integer);
                }else{
                    map.put(zdeviceDictSlave.getDictname(),zdeviceDictSlave.getConnum());
                }
            }
        }
        //转换为List
        List<ZdeviceDictDTO> list = new ArrayList<>();
        for (Map.Entry<String,Integer> entry: map.entrySet()) {
            ZdeviceDictDTO zdeviceDictDTO = new ZdeviceDictDTO(entry.getKey(),entry.getValue());
            list.add(zdeviceDictDTO);
        }
        return list;
    }
}
