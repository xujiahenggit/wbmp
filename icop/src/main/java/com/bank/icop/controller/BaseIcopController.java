package com.bank.icop.controller;

import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Andy
 * @Date: 2020/6/4 15:57
 */
public class BaseIcopController {
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
