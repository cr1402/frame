package com.kejin.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.kejin.service.impl.UserDetailsServiceImpl;
import com.kejin.utils.CommonUtils;
import com.kejin.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("authorization");

        if (CommonUtils.strIsNull(token)){
            filterChain.doFilter(request,response);
            return;
        }
        String token1 ="";
        try {
            boolean verify = jwtUtils.verify(token);

            if(verify){
                token1 = jwtUtils.getToken(jwtUtils.getTokenId(token));
            }
            response.setHeader("authorization",token1);
        } catch (Exception e) {
//            e.printStackTrace();
            resolver.resolveException(request, response, null, new TokenExpiredException("token非法"));
            return;
        }

        Integer tokenId = null;
        try {
            tokenId = Integer.valueOf(jwtUtils.getTokenId(token1));
        } catch (Exception e) {
            resolver.resolveException(request, response, null, new JWTDecodeException("token非法"));
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(tokenId);

        if (CommonUtils.objectIsNull(userDetails)){
            resolver.resolveException(request, response, null, new RuntimeException("该用户未登录"));
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }
}
