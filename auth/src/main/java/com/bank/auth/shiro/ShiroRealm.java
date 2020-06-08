package com.bank.auth.shiro;

import com.bank.auth.dto.AuthDTO;
import com.bank.auth.dto.PermissionDTO;
import com.bank.auth.jwt.JwtToken;
import com.bank.auth.service.PermissionService;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.JwtUtil;
import com.bank.core.utils.PropertyUtil;
import com.bank.role.dos.RoleDO;
import com.bank.role.service.RoleService;
import com.bank.user.dos.UserDO;
import com.bank.user.service.UserService;
import com.google.common.base.Strings;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * ClassName: ShiroRealm
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/10 10:08:49
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Lazy
    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private RoleService roleService;

    @Lazy
    @Autowired
    private PermissionService permissionService;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        AuthDTO authDTO = (AuthDTO) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 设置用户角色字符串
        Set<String> roleCodes = new HashSet<>();
        for (RoleDO role : authDTO.getRoles()) {
            roleCodes.add(role.getRoleCode());
        }
        info.setRoles(roleCodes);
        //设置用户权限字符串
        Set<String> permCodes = new HashSet<>();
        for (PermissionDTO perm : authDTO.getPermissions()) {
            permCodes.add(perm.getPermissionCode());
        }
        info.setStringPermissions(permCodes);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();// 校验token有效性
        TokenUserInfo userinfo = JwtUtil.getUserInfo(token);
        if (Strings.isNullOrEmpty(userinfo.getUserId())) {
            throw new AuthenticationException("用户ID不能为空，token非法无效!");
        }

        UserDO userDO = this.userService.getById(userinfo.getUserId());
        if (userDO == null) {
            throw new UnknownAccountException("用户不存在");
        }

        if (!JwtUtil.verify(token, userinfo, userDO.getPassword())) {
            throw new AuthenticationException("token过期或者认证失败！");
        }

        // 设置角色和权限，其它地方通过SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息
        AuthDTO authDTO = permissionService.getAuthDTO(userDO.getUserId());

        //String password = userDO.getPassword();
        PropertyUtil.copyProperties(userDO, authDTO, "password");
        Object password = new SimpleHash("MD5", token, null, 32);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authDTO, password, getName());
        return info;
    }


}
