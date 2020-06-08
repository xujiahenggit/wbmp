package com.bank.manage.service;

import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.StyleAreaDO;
import com.bank.manage.dto.StyleAreaDTO;

import java.util.List;

public interface StyleAreaService {
    Boolean saveStyleArea(StyleAreaDTO styleAreaDTO, TokenUserInfo tokenUserInfo);

    List<StyleAreaDO> queryStyle(String deviceType);
}
