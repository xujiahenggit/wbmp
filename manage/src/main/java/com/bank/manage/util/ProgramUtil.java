package com.bank.manage.util;

import com.alibaba.fastjson.JSONArray;
import com.bank.manage.vo.PlayProgramVo;
import com.bank.manage.vo.ProgramVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Andy
 * @Date: 2020/4/29 9:13
 */
@Slf4j
public class ProgramUtil {

    public static List<Map<String,Object>> getProgramMap(List<ProgramVo> programVoList,String httpPath){
        List<Map<String,Object>> listMap=new ArrayList<>();
        Map<Integer, List<ProgramVo>> map = programVoList.stream().collect(Collectors.groupingBy(ProgramVo::getProgramId));
        for (Map.Entry<Integer, List<ProgramVo>> entry : map.entrySet()) {
            //System.out.println("===="+entry.getValue());
            log.info("节目信息：{}",entry.getValue());
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
                //System.out.println("entry1："+entry1);
                log.info("entry1：{}",entry1);
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
                        pv.setMaterialPath(httpPath+value.get(i).getMaterialPath());
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
            msg.put("layoutHeight",program.get(0).getLayoutHeight());
            msg.put("layoutWidth",program.get(0).getLayoutWidth());
            msg.put("name",program.get(0).getProgramName());
            msg.put("layoutBackground",program.get(0).getLayoutBackground());

            mapWapper.put("deviceNo",program.get(0).getTerminalNum());
            mapWapper.put("orgId",program.get(0).getOrgId());
            mapWapper.put("code","000000");

            mapWapper.put("msg",msg);
            //HttpUtil.sendPost(configFileReader.getMESSAGE_PATH(),mapWapper);
            //System.out.println(JSON.toJSON(mapWapper));
            listMap.add(mapWapper);
        }
        return listMap;
    }
}
