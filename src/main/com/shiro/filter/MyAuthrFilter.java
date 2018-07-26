package com.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyAuthrFilter extends AuthorizationFilter{
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] rolesOrPerms = (String[]) o;
        //无需权限
        if(rolesOrPerms == null || rolesOrPerms.length == 0)
            return true;
        //角色验证，or验证，一个通过则放行
        for (String roleOrPerm: rolesOrPerms ) {
            System.out.println(roleOrPerm);
            System.out.println(subject.hasRole(roleOrPerm));
            System.out.println(subject.isPermitted(roleOrPerm));
            System.out.println();
            System.out.println();
            if(subject.hasRole(roleOrPerm) || subject.isPermitted(roleOrPerm))
                return true;
        }
        //权限验证

        return false;
    }
}
