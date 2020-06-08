package com.bank.auth.util;

import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.JwtUtil;
import com.bank.user.dos.UserDO;
import com.bank.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TokenUtil {

    @Resource
    UserService userService;

    public String getToken(String username){
        if (StringUtils.isEmpty(username)) {
            throw new BizException("用户名不能为空");
        }
        UserDO userDO = userService.getById(username);
        if (userDO == null) {
            throw new BizException("该用户不存在");
        }
        TokenUserInfo userinfo = TokenUserInfo.builder().userId(username)
                .userName(userDO.getUserName())
                .orgId(userDO.getOrgId()).orgName(userDO.getOrgName()).build();

        return JwtUtil.sign(userinfo, userDO.getPassword());
    }
}
