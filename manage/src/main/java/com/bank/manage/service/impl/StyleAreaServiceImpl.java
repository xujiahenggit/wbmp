package com.bank.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.NetUtil;
import com.bank.core.utils.StringSplitUtil;
import com.bank.manage.dao.StyleAreaDao;
import com.bank.manage.dos.StyleAreaDO;
import com.bank.manage.dto.StyleAreaDTO;
import com.bank.manage.service.StyleAreaService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class StyleAreaServiceImpl implements StyleAreaService {

    @Autowired
    private StyleAreaDao styleAreaDao;

    @Resource
    private ConfigFileReader configFileReader;

    @Override
    public Boolean saveStyleArea(StyleAreaDTO styleAreaDTO, TokenUserInfo tokenUserInfo) {
        Boolean flag = null;
        String style = JSON.toJSONString(styleAreaDTO.getStyle());
        String splitStylePath = StringSplitUtil.splitMaterialPath(styleAreaDTO.getStylePath(), netUtil.getUrlSuffix(""));
        try {
            StyleAreaDO styleAreaDO = StyleAreaDO.builder()
                    .styleName(styleAreaDTO.getStyleName())
                    .styleType(styleAreaDTO.getStyleType())
                    .style(style)
                    .deviceType(styleAreaDTO.getDeviceType())
                    .stylePath(splitStylePath)
                    .createdUser(tokenUserInfo.getUserId())
                    .createdTime(LocalDateTime.now())
                    .layoutHeight(styleAreaDTO.getLayoutHeight())
                    .layoutWidth(styleAreaDTO.getLayoutWidth())
                    .layoutBackground(styleAreaDTO.getLayoutBackground())
                    .build();
            styleAreaDao.insert(styleAreaDO);
            flag = true;
            log.info("样式保存成功，样式数据：{}",styleAreaDO);
        } catch (Exception e) {
            flag = false;
            log.error("样式保存失败，样式数据："+e.getMessage());
        }
        return flag;
    }

    @Resource
    NetUtil netUtil;

    @Override
    public List<StyleAreaDO> queryStyle(String deviceType) {
        QueryWrapper<StyleAreaDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DEVICE_TYPE",deviceType);
        List<StyleAreaDO> styleAreaDOList = styleAreaDao.selectList(queryWrapper);
        String suffix = netUtil.getUrlSuffix();
        for (StyleAreaDO styleAreaDO:styleAreaDOList) {
            styleAreaDO.setStylePath(suffix+styleAreaDO.getStylePath());
        }
        return styleAreaDOList;
    }
}
