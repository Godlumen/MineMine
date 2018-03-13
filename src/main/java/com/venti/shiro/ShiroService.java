package com.venti.shiro;

import com.venti.dao.repository.SysPermissionInitRepository;
import com.venti.model.po.SysPermission;
import com.venti.model.po.SysPermissionInit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShiroService {

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    private SysPermissionInitRepository sysPermissionInitRepository;

    /**
     * 初始化权限
     *
     * @return
     */
    public Map<String, String> loadFilterChainDefinitions() {

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        List<SysPermissionInit> list = sysPermissionInitRepository.findAll();

        for (SysPermissionInit sysPermissionInit : list) {
            filterChainDefinitionMap.put(sysPermissionInit.getUrl(), sysPermissionInit.getPermissionInit());
        }
        return filterChainDefinitionMap;
    }

    /**
     * 更新权限
     */
    public void updatePermission() {
        synchronized (shiroFilterFactoryBean) {

            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            //清空旧权限
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            //重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }

            System.out.println("更新权限成功！");
            log.info("更新权限成功！");
        }
    }

}
