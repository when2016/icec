package org.icec.web.config;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.icec.web.shiro.credential.BCryptPasswordService;
import org.icec.web.shiro.filter.JWTOrFormAuthenticationFilter;
import org.icec.web.shiro.realm.MyFormRealm;
import org.icec.web.shiro.realm.MyJWTRealm;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ShiroConfig {
	private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();  
	   
    @Bean(name = "realms")
	@Autowired
	public List<Realm> getRealms(MyJWTRealm jWTRealm,MyFormRealm formRealm) {
		List<Realm> realms=new ArrayList<Realm>();
		realms.add(jWTRealm);
		final PasswordMatcher passwordMatcher = new PasswordMatcher();
		/*HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
		matcher.setHashIterations(2);
		matcher.setHashAlgorithmName("md5");*/
		passwordMatcher.setPasswordService(new BCryptPasswordService());
		formRealm.setCredentialsMatcher(passwordMatcher);
		realms.add(formRealm);
		return realms;
	}
    @Bean(name = "shiroEhcacheManager")  
    public EhCacheManager getEhCacheManager() {  
        EhCacheManager em = new EhCacheManager();  
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");  
        return em;  
    }  
  
    @Bean(name = "lifecycleBeanPostProcessor")  
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {  
        return new LifecycleBeanPostProcessor();  
    }  
  
    @Bean  
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {  
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();  
        daap.setProxyTargetClass(true);  
        return daap;  
    }  
  
    @Bean(name = "securityManager")  
    @Autowired
    public DefaultWebSecurityManager getDefaultWebSecurityManager(List<Realm> realms) {  
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();  
        dwsm.setRealms(realms);
        dwsm.setCacheManager(getEhCacheManager());  
        return dwsm;  
    }  
  
    @Bean  
    @Autowired
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {  
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();  
        aasa.setSecurityManager(defaultWebSecurityManager);  
        return new AuthorizationAttributeSourceAdvisor();  
    }  
  
    @Bean(name = "shiroFilter")  
    @Autowired
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {  
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();  
        shiroFilterFactoryBean  
                .setSecurityManager(defaultWebSecurityManager);  
        Map<String, Filter> filters=new HashMap<String, Filter>();
        JWTOrFormAuthenticationFilter filter= new JWTOrFormAuthenticationFilter();
        filter.setLoginUrl("/sys/login");
        filter.setSuccessUrl("/");
        filters.put("authc", filter);
        //filterChainDefinitionMap.put("/*.html", "anon");  
        //filterChainDefinitionMap.put("/html/*.html", "anon"); 
        filterChainDefinitionMap.put("/ace/**", "anon"); 
        filterChainDefinitionMap.put("/layui/**", "anon");  
        filterChainDefinitionMap.put("/my/**", "anon");  
        filterChainDefinitionMap.put("/favicon.ico", "anon"); 
        filterChainDefinitionMap.put("/kaptcha.jpg", "anon");
       // filterChainDefinitionMap.put("/third/**", "anon"); 
       // filterChainDefinitionMap.put("/upload/**", "anon"); 
       // filterChainDefinitionMap.put("/public/**", "anon"); 
        filterChainDefinitionMap.put("/**", "authc");  
        
        shiroFilterFactoryBean  
                .setFilterChainDefinitionMap(filterChainDefinitionMap); 
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;  
    } 
}
