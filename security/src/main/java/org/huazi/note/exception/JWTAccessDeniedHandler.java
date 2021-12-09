package org.huazi.note.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有访问权限
 *
 * @author huazi
 * @date 2021/12/7 18:12
 */
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    @SneakyThrows
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException exception) {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        String reason = "统一处理，原因：" + exception.getMessage();
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(reason));
    }
}
