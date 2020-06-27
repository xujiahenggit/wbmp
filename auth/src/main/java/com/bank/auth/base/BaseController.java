package com.bank.auth.base;

import javax.servlet.http.HttpServletRequest;

import com.bank.core.utils.JwtUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;

/**
 * @Author: Andy
 * @Date: 2020/4/21 16:06
 */
public class BaseController {

    /**
     * 获取当前登录用户信息
     * @param request
     * @return
     */
    protected TokenUserInfo getCurrentUserInfo(HttpServletRequest request) {
        HttpServletRequest req = request;
        String token = req.getHeader("Authorization");
        if (token == null) {
            throw new BizException("获取当前用户失败，接口请求Header中未携带Token！");
        }
        TokenUserInfo user = JwtUtil.getUserInfo(token);
        if (user == null) {
            throw new BizException("获取当前用户失败，Token解析异常！");
        }
        return JwtUtil.getUserInfo(token);
    }

    protected String getCurrentUserId(HttpServletRequest request) {
        return this.getCurrentUserInfo(request).getUserId();
    }
}
