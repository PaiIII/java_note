package org.huazi.note.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.huazi.note.entity.JwtUser;
import org.huazi.note.model.LoginUser;
import org.huazi.note.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 用户验证
 * 1、继承UsernamePasswordAuthenticationFilter
 * 2、获取用户登录信息
 *
 * @author huazi
 * @date 2021/12/7 17:33
 */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //是否开启记住我
    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;

    /**
     * 提供有参构造，修改默认的登录路径
     *
     * @param authenticationManager
     * @link UsernamePasswordAuthenticationFilter:DEFAULT_ANT_PATH_REQUEST_MATCHER
     */
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    /**
     * 从输入流中获取到登录的信息
     *
     * @param request
     * @param response
     * @return
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
        rememberMe.set(loginUser.getRememberMe() == null ? 0 : loginUser.getRememberMe());
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));
    }

    /**
     * 成功验证后调用的方法:如果验证成功，就生成token并返回
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {

        //getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        log.info("获取jwtUser：{}", jwtUser.toString());
        boolean isRemember = rememberMe.get() == 1;

        String role = "";
        // 因为在JwtUser中存了权限信息，可以直接获取，由于只有一个角色就这么干了
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }

        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), role, isRemember);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的时候应该是 `Bearer token`
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
    }

    /**
     * 验证失败时候调用的方法
     *
     * @param request
     * @param response
     * @param failed
     */
    @SneakyThrows
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }

}
