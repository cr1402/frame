package com.kejin.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.Iterator;

public class PathAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        //进行权限匹配，如果用户拥有资源权限那么进行放行操作
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            // 当前请求需要的权限
            String needRole = ca.getAttribute();

            if ("login".equalsIgnoreCase(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    //匿名用户
                    throw new AccessDeniedException("资源信息不存在");
                } else {
                    //登录用户
                    return;
                }
            }
            // 当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equalsIgnoreCase(needRole)) {
                    return;
                }
            }
        }
        //如果当前请求没有验证，返回未验证异常
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new AccessDeniedException("用户未登录");
        }
        throw new AccessDeniedException("权限不足!");
    }
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
