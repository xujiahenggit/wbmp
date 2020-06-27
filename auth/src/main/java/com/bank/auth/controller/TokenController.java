package com.bank.auth.controller;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bank.auth.dto.AuthDTO;
import com.bank.auth.util.TokenUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.JwtUtil;
import com.bank.core.utils.RedisUtil;
import com.bank.role.dos.RoleDO;
import com.bank.role.service.RoleService;
import com.bank.user.dos.UserDO;
import com.bank.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "认证授权接口")
@RestController
public class TokenController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
    })
    @PostMapping("/login")
    public AuthDTO login(String username, String password) {

        if (null == username) {
            throw new BizException("用户名不能为空");
        }
        if (null == password) {
            throw new BizException("密码不能为空");
        }

        Subject currentUser = SecurityUtils.getSubject();
        //登录
        currentUser.login(new UsernamePasswordToken(username, password));
        //从session取出用户信息
        AuthDTO authDTO = (AuthDTO) currentUser.getPrincipal();
        if (authDTO == null) {
            throw new AuthenticationException();
        }
        //返回登录用户的信息给前台，含用户的所有角色和权限
        return authDTO;
    }

    @Resource
    RedisUtil redisUtil;

    @Resource
    TokenUtil tokenUtil;

    @ApiOperation(value = "单点登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true)
    })
    @PostMapping("/ssoLogin")
    public Object ssoLogin(String username) {
        UserDO userDO = this.userService.getById(username);
        if (userDO == null) {
            throw new BizException("该用户不存在");
        }

        String token = this.tokenUtil.getToken(username);
        Set<RoleDO> roles = this.roleService.listByUserId(userDO.getUserId());
        Set<String> roleCodes = new HashSet<>();
        for (RoleDO role : roles) {
            roleCodes.add(role.getRoleCode());
        }
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("userInfo", userDO);
        obj.put("roleInfo", roleCodes);
        this.redisUtil.set(token, "");
        return obj;
    }

    @ApiOperation(value = "用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
    })
    @PostMapping("/userLogin")
    public JSONObject userLogin(String username, String password) {

        if (null == username) {
            throw new BizException("用户名不能为空");
        }
        if (null == password) {
            throw new BizException("密码不能为空");
        }
        UserDO userDO = this.userService.getById(username);
        if (userDO == null) {
            throw new BizException("该用户不存在");
        }

        if(userDO.getUserWorkStatus().equals("辞职") || userDO.getUserWorkStatus().equals("退休") || userDO.getUserWorkStatus().equals("内退") || userDO.getUserWorkStatus().equals("辞退")){
            throw new BizException("不在岗用户,禁止登录！");
        }

        //不用盐值校验密码
        SimpleHash simpleHash = new SimpleHash(Md5Hash.ALGORITHM_NAME, password, null, 32);
        if (!simpleHash.toHex().equals(userDO.getPassword())) {
            throw new BizException("用户或密码不正确");
        }

        TokenUserInfo userinfo = TokenUserInfo.builder().userId(username)
                .userName(userDO.getUserName())
                .orgId(userDO.getOrgId()).orgName(userDO.getOrgName()).build();

        String token = JwtUtil.sign(userinfo, simpleHash.toHex());
        Set<RoleDO> roles = this.roleService.listByUserId(userDO.getUserId());
        Set<String> roleCodes = new HashSet<>();
        for (RoleDO role : roles) {
            roleCodes.add(role.getRoleCode());
        }
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("userInfo", userDO);
        obj.put("roleInfo", roleCodes);
        this.redisUtil.set(token, "");
        return obj;
    }

    @ApiOperation(value = "用户注销")
    @GetMapping("/logout")
    public void loginout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

}
