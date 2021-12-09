package org.huazi.note.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO 异常消息统一处理：有携带token或者token无效
 *
 * @author huazi
 * @date 2021/12/7 18:11
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException exception) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        String reason = "统一处理，原因：" + exception.getMessage();
        response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
    }
}
