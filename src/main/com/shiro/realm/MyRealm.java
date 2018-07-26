package com.shiro.realm;

import com.shiro.entity.Users;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.dao.Dao;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private Dao dao;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = queryRolesByUsername(username);
        Set<String> permissions = queryPermissionsByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.从主体传过来的认证信息获得用户名
        String username = (String) authenticationToken.getPrincipal();
        //2.通过用户名到数据库中获取凭证
        Users user = getPasswordByUsername(username);
        if (user == null)
            return null;

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(), "myRealm");
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getPasswrdSalt()));
        return info;
    }

    private Users getPasswordByUsername(String username) {
        return dao.getUserByUsername(username);
    }

    private Set<String> queryRolesByUsername(String username) {
        return new HashSet<String>(dao.queryRolesByUsername(username));
    }

    private Set<String> queryPermissionsByUsername(String username) {
        return new HashSet<String>(dao.queryPermissionsByUsername(username));
    }

    public static void main(String[] args) {
        //zhang
        Md5Hash md5Hash = new Md5Hash("123","abc");
        System.out.println(md5Hash.toString());
        //wang
        Md5Hash md5Hash1 = new Md5Hash("456","abc");
        System.out.println(md5Hash1.toString());
    }
}
