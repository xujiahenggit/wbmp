package com.bank.auth.base;

import javax.servlet.http.HttpServletRequest;

import com.bank.core.utils.JwtUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.user.dos.OrganizationDO;
import com.bank.user.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Andy
 * @Date: 2020/4/21 16:06
 */
public class BaseController {

    @Autowired
    private OrganizationService organizationService;

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

    protected String getCurrentOrgcode(HttpServletRequest request) {
        HttpServletRequest req = request;
        String token = req.getHeader("Authorization");
        if (token == null) {
            throw new BizException("获取当前用户失败，接口请求Header中未携带Token！");
        }
        TokenUserInfo user = JwtUtil.getUserInfo(token);
        OrganizationDO organizationDO = this.organizationService.getById(user.getOrgId());
        if (user == null) {
            throw new BizException("获取当前用户失败，Token解析异常！");
        }
        if(organizationDO == null){
            return null;
        }
        String orgCode = organizationDO.getOrgCode();
        if(orgCode.length() == 6){
            orgCode = orgCode.concat("01");
        }
        return orgCode;
    }
}
