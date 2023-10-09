package com.kejin.config.security;

import com.kejin.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author 陈
 */
@Component
public class RolePermissionMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private RoleServiceImpl service;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation invocation = (FilterInvocation) object;
        String url = invocation.getRequestUrl();

        Collection<ConfigAttribute> roles = service.getRoleCodeByUrl(url);

        if (roles != null && roles.size() > 0) {
            return roles;
        }

        if ("/user/login".equals(url)){
            return null;
        }

        return SecurityConfig.createList("login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<String> allRole = service.getAllRoleCode();

        Collection<ConfigAttribute> roles = (Collection<ConfigAttribute>)(Collection)allRole;
        roles.addAll(SecurityConfig.createList("login"));
        return roles;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
