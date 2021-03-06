package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.NetUtil;
import com.bank.manage.dao.DeviceDao;
import com.bank.manage.dao.DevicePlayDao;
import com.bank.manage.dos.DeviceDO;
import com.bank.manage.dos.DevicePlayDO;
import com.bank.manage.dos.MaterialDO;
import com.bank.manage.dos.PlayAreaMaterialDO;
import com.bank.manage.dto.DevicePlayDTO;
import com.bank.manage.service.DevicePlayService;
import com.bank.manage.service.MaterialService;
import com.bank.manage.service.PlayAreaMaterialService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DevicePlayServiceImpl extends ServiceImpl<DevicePlayDao,DevicePlayDO> implements DevicePlayService {

    @Autowired
    private DevicePlayDao devicePlayDao;

    @Resource
    private DeviceDao deviceDao;

    @Override
    public Boolean saveDevicePlay(DevicePlayDTO devicePlayDTO) {
        Boolean flag = null;
        try {
            DevicePlayDO devicePlayDO  =DevicePlayDO.builder()
                    .terminalNum(devicePlayDTO.getTerminalNum())
                    .mac(devicePlayDTO.getMac())
                    .messageLog(devicePlayDTO.getMessageLog())
                    .programId(devicePlayDTO.getProgramId())
                    .programName(devicePlayDTO.getProgramName())
                    .createTime(LocalDateTime.now()).build();
            devicePlayDao.insert(devicePlayDO);

            QueryWrapper<DeviceDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("TERMINAL_NUM",devicePlayDTO.getTerminalNum());
            DeviceDO deviceDO = deviceDao.selectOne(queryWrapper);
            deviceDO.setProgramId(devicePlayDTO.getProgramId());
            deviceDO.setProgramName(devicePlayDTO.getProgramName());
            deviceDao.updateById(deviceDO);
            flag = true;
        } catch (Exception e) {
            flag = false;
            log.error("设备播放信息更新失败："+e.getMessage());
        }
        return flag;
    }

    @Override
    public IPage<DevicePlayDO> queryDevicePlay(PageQueryModel pageQueryModel) {
        Page<DevicePlayDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        //条件查询
        QueryWrapper<DevicePlayDO> queryWrapper = new QueryWrapper<>();
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        if(queryParam.get("terminalNum") !=null && !"".equals(queryParam.get("terminalNum"))){
            String terminalNum = (String) queryParam.get("terminalNum");
            queryWrapper.eq(StringUtils.isNotBlank(terminalNum), "TERMINAL_NUM", terminalNum);
        }

        String startTime = "";
        String endTime = "";
        if(queryParam.get("startTime") !=null  ){
            startTime = (String) queryParam.get("startTime");
        }
        if(queryParam.get("endTime") !=null ){
            endTime = (String) queryParam.get("endTime");
        }
        if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
            queryWrapper.between("CREATE_TIME",startTime,endTime);
        }
        return devicePlayDao.selectPage(page,queryWrapper);
    }

    @Resource
    private PlayAreaMaterialService playAreaMaterialService;


    @Resource
    private MaterialService materialService;

    /*@Value("${MATERIAL.HTTP_PATH}")
    private String basePath;*/
    @Resource
    NetUtil netUtil;

    @Override
    public List<Map<String, Object>> queryPlayLatestTwo() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();

        List<DevicePlayDO> devicePlayDOS = devicePlayDao.queryPlayLatestTwo();
        for (DevicePlayDO devicePlayDO : devicePlayDOS) {
            HashMap<String, Object> map = new HashMap<>();
            Integer programId = devicePlayDO.getProgramId();
            List<PlayAreaMaterialDO> areaMaterialDOS = playAreaMaterialService.list(new LambdaQueryWrapper<PlayAreaMaterialDO>().eq(PlayAreaMaterialDO::getProgramId,programId));
            if (areaMaterialDOS.size()<1){
                  throw new BizException("节目ID "+programId+" 下未关联素材");
            }else {
                //尝试从返回的列表中查找图片素材
                Optional<PlayAreaMaterialDO> first = areaMaterialDOS.stream().filter((playAreaMaterialDO) -> playAreaMaterialDO.getType().equals("00")).findFirst();
                //如果图片为空就随机选择一个
                PlayAreaMaterialDO playAreaMaterialDO =first.isPresent()?first.get():areaMaterialDOS.get(0);
                MaterialDO materialDO = materialService.getById(playAreaMaterialDO.getMaterialId());
                map.putAll(BeanUtil.beanToMap(materialDO));
            }
            map.putAll(BeanUtil.beanToMap(devicePlayDO));
            list.add(map);
        }

        return   list.stream().map(map -> {
            map.put("materialPath", netUtil.getUrlSuffix("") + map.get("materialPath"));
            return map;
        }).collect(Collectors.toList());
    }
}
