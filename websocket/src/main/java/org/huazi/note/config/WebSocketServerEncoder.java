package org.huazi.note.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author huazi
 * @date 2021/12/3 17:05
 */
public class WebSocketServerEncoder implements Encoder.Text<SecurityProperties.User> {

    @Override
    public void destroy() {
    }

    @Override
    public void init(EndpointConfig arg0) {
    }

    @Override
    public String encode(SecurityProperties.User responseMessage) {
        return JSONObject.toJSONString(responseMessage);
    }
}