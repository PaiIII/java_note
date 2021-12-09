package org.huazi.note.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.huazi.note.exception.TokenIsExpiredException;
import org.huazi.note.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * 鉴权
 *
 * @author huazi
 * @date 2021/12/7 17:52
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @SneakyThrows
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        log.info("获取token：{}", tokenHeader);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request, response, filterChain);
    }

    // 这里从token中获取用户信息并新建一个token TODO 返回消息（异常）统一用result格式封装
    @SneakyThrows
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        boolean expiration = JwtTokenUtils.isExpiration(token);
        if (expiration) {
            throw new TokenIsExpiredException("token超时了");
        } else {
            String username = JwtTokenUtils.getUsername(token);
            String role = JwtTokenUtils.getUserRole(token);
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null,
                        Collections.singleton(new SimpleGrantedAuthority(role))
                );
            }
        }
        return null;
    }


}
