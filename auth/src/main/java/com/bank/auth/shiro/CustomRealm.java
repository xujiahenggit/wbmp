package com.bank.auth.shiro;

import com.bank.auth.dto.AuthDTO;
import com.bank.auth.dto.PermissionDTO;
import com.bank.auth.service.PermissionService;
import com.bank.core.utils.PropertyUtil;
import com.bank.role.dos.RoleDO;
import com.bank.role.service.RoleService;
import com.bank.user.dos.UserDO;
import com.bank.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.Set;

/**
 * 认证授权
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
public class CustomRealm extends AuthorizingRealm {

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
     * 设置密码加盐解析
     *
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 加密算法
        matcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        // 加密次数
        matcher.setHashIterations(32);
        super.setCredentialsMatcher(matcher);
    }

    /**
     * 设置用户角色和权限逻辑
     *
     * @param principals
     * @return
     */
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

    /**
     * 获取用户的角色和权限，做登陆和授权的时候使用
     * 1.获取用户角色
     * 2.获取用户权限列表
     *
     * @param token 认证令牌
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        if (username == null) {
            throw new AccountException("用户名不能为空");
        }
        UserDO userDO = this.userService.getById(username);
        if (userDO == null) {
            throw new UnknownAccountException("用户不存在");
        }

        // 设置角色和权限，其它地方通过SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息
        Set<RoleDO> roles = this.roleService.listByUserId(userDO.getUserId());
        Set<Integer> roleIds = new HashSet<>();
        for (RoleDO role : roles) {
            roleIds.add(role.getRoleId());
        }
        AuthDTO authDTO = new AuthDTO();
        Set<PermissionDTO> perms = new HashSet<>();
        if (roleIds.size() == 0) {
            authDTO.setRoles(roles);
            authDTO.setPermissions(perms);
        } else {
            // 设置角色
            authDTO.setRoles(roles);
            // 设置权限
            perms = this.permissionService.listByRoleIds(roleIds);
            authDTO.setPermissions(perms);
        }

        //从数据库中取出密码，密码是加密的
        String password = userDO.getPassword();
        if (new String(upToken.getPassword()).equals("ssoLogin")) {
            password = new SimpleHash(Md5Hash.ALGORITHM_NAME, "ssoLogin", null, 32).toString();
        }
        PropertyUtil.copyProperties(userDO, authDTO, "password");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authDTO, password, getName());
        return info;
    }

    public static void main(String[] args) {

        String hashAlgorithmName = Md5Hash.ALGORITHM_NAME;//加密方式

        Object crdentials = "88888888";//密码原值

        Object salt = null;//盐值

        int hashIterations = 32;//加密1024次

        Object result = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);

        System.out.println(result);

    }
}