package com.bank.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.HttpUtil;
import com.bank.core.utils.NetUtil;
import com.bank.manage.dto.MaterialDTO;
import com.bank.manage.dto.PlayAreaDTO;
import com.bank.manage.service.PlayAreaMaterialService;
import com.bank.manage.util.ProgramUtil;
import com.bank.manage.vo.PlayAreaMaterialVo;
import com.bank.manage.vo.PlayProgramVo;
import com.bank.manage.vo.ProgramVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Api(tags = "节目发布信息接口")
@RestController
@RequestMapping("/program")
@Slf4j
public class PlayAreaMaterialController {

    @Autowired
    private PlayAreaMaterialService playAreaMaterialService;

    @Resource
    private ConfigFileReader configFileReader;

    @Resource
    NetUtil netUtil;
    @PostMapping("/save")
    @ApiOperation(value = "节目发布接口")
    @ApiImplicitParam(name = "playAreaDTO", value = "节目信息", required = true, paramType = "body", dataType = "PlayAreaDTO")

    public Boolean save(@RequestBody PlayAreaDTO playAreaDTO){
       if(playAreaDTO.getDeviceList() == null || playAreaDTO.getDeviceList().size()==0){
           throw new BizException("请选择播放设备");
       }
        if(playAreaDTO.getPlayAreaMateriaList() == null || playAreaDTO.getPlayAreaMateriaList().size() == 0){
            throw new BizException("请对播放区域进行设置");
        }
        if(playAreaDTO.getProgramDTO() == null){
            throw new BizException("请设置节目项目信息");
        }
        return playAreaMaterialService.save(playAreaDTO.getDeviceList(),playAreaDTO.getPlayAreaMateriaList(),playAreaDTO.getProgramDTO());
    }

    @PostMapping("/queryList")
    @ApiOperation(value = "节目发布列表查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "节目发布信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<PlayAreaMaterialVo> queryList(@RequestBody PageQueryModel pageQueryModel){
        return playAreaMaterialService.queryList(pageQueryModel);
    }


    @PostMapping("/queryMaterialList")
    @ApiOperation(value = "节目发布素材列表查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "节目发布素材信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<MaterialDTO> queryMaterialList(@RequestBody PageQueryModel pageQueryModel){
        return playAreaMaterialService.queryMaterialList(pageQueryModel);
    }


    @GetMapping("query")
    public void queryList(){
        List<ProgramVo> programVoList = playAreaMaterialService.queryProgramList();
        log.info("{}",programVoList);
        List<Map<String,Object>> listMap= ProgramUtil.getProgramMap(programVoList,netUtil.getUrlSuffix(""));
        for (Map<String,Object> item:listMap ) {
            HttpUtil.sendPost(netUtil.getUrlSuffix()+"/jms/topic",item);
            System.out.println(JSON.toJSON(item));
        }
        /*Map<Integer, List<ProgramVo>> map = programVoList.stream().collect(Collectors.groupingBy(ProgramVo::getProgramId));

        for (Map.Entry<Integer, List<ProgramVo>> entry : map.entrySet()) {
            System.out.println("===="+entry.getValue());
            Map<String,Object> mapWapper = new HashMap<>();
            Map<String,Object> msg = new HashMap<>();
            List<Map<String,Object>> dataList = new ArrayList<>();


            List<ProgramVo> program = entry.getValue();//获取value
            List<PlayProgramVo> playProgramVoList = new ArrayList<>();
            for (ProgramVo pro:program) {
                PlayProgramVo pv = new PlayProgramVo();
                pv.setAreaName(pro.getAreaName());
                pv.setAreaNum(pro.getAreaNum());
                pv.setMaterialPath(pro.getMaterialPath());
                pv.setPlayTime(pro.getPlayTime());
                pv.setSort(pro.getSort());
                pv.setMaterialType(pro.getType());
                pv.setMaterialId(String.valueOf(pro.getMaterialId()));
                pv.setMaterialName(pro.getMaterialName());
                pv.setAreaType(pro.getAreaType());
                pv.setText(pro.getText());
                playProgramVoList.add(pv);
            }
            Map<String, List<PlayProgramVo>> collect = playProgramVoList.stream().collect(Collectors.groupingBy(PlayProgramVo::getAreaNum));

            for (Map.Entry<String, List<PlayProgramVo>> entry1 : collect.entrySet()) {
                System.out.println("entry1："+entry1);
                List<PlayProgramVo> dataMapList = new ArrayList<>();
                Map<String,Object> dataMap = new HashMap<>();
                List<PlayProgramVo> value = entry1.getValue();
                for (int i = 0; i < value.size(); i++) {
                    PlayProgramVo pv = new PlayProgramVo();
                    pv.setPlayTime(value.get(i).getPlayTime());
                    pv.setMaterialId(value.get(i).getMaterialId());
                    pv.setMaterialType(value.get(i).getMaterialType());
                    pv.setMaterialName(value.get(i).getMaterialName());
                    pv.setSort(value.get(i).getSort());
                    if(StringUtils.isNotBlank(value.get(i).getMaterialPath())){
                        pv.setMaterialPath(netUtil.getUrlSuffix("")+value.get(i).getMaterialPath());
                    }
                    if(StringUtils.isNotBlank(value.get(i).getText())){
                        pv.setText(value.get(i).getText());
                    }
                    dataMapList.add(pv);
                }
                dataMap.put("contentList",dataMapList);
                dataMap.put("layoutId",value.get(0).getAreaNum());
                dataMap.put("areaType",value.get(0).getAreaType());
                dataList.add(dataMap);
            }

            msg.put("data",dataList);
            msg.put("layout", JSONArray.parseArray(program.get(0).getStyle()));
            msg.put("programId",program.get(0).getProgramId());
            msg.put("layoutHeight","1080");
            msg.put("layoutWidth","1920");
            msg.put("name",program.get(0).getProgramName());

            mapWapper.put("deviceNo",program.get(0).getTerminalNum());
            mapWapper.put("orgId",program.get(0).getOrgId());
            mapWapper.put("code","000000");

            mapWapper.put("msg",msg);

            HttpUtil.sendPost(configFileReader.getMESSAGE_PATH(),mapWapper);
            System.out.println(JSON.toJSON(mapWapper));
        }*/

    }


}
