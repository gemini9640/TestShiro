package com.shiro;

import com.shiro.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyRealmTest {
    private ClassPathXmlApplicationContext ctx;
    private MyRealm myRealm;

    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext("classpath:conf/spring/spring.xml");
        myRealm = (MyRealm) ctx.getBean("myRealm");
    }

    @Test
    public void testMyRealm() {
        //1.构建SecurityManage环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(myRealm);
        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        myRealm.setCredentialsMatcher(matcher);

        subject.login(token);

        subject.checkRoles("admin", "admin1");
        subject.checkPermissions("user:add", "user:delete");
    }
}
