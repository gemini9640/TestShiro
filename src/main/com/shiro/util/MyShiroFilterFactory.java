package com.shiro.util;

import com.shiro.dao.Dao;
import com.shiro.entity.RolesPermissions;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroFilterFactory extends ShiroFilterFactoryBean{


    @Autowired
    private Dao dao;
    @Override
    public void setFilterChainDefinitions(String definitions) {
        System.out.println("test");
        List<RolesPermissions> list = dao.queryAllRolePermissions();
        StringBuilder sb = new StringBuilder();
        for(RolesPermissions rp : list)
            //字符串拼接权限
            sb.append(rp.getUrl() + " = myAuthrFilter["+rp.getRole_name()+", "+rp.getPermission()+"]\n");
        definitions += sb.append("/authr/* = authc").toString();
        System.out.println(definitions);


        //从配置文件加载权限配置
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        setFilterChainDefinitionMap(section);
    }
}
