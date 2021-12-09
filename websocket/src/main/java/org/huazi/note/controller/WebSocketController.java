package org.huazi.note.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.huazi.note.server.WebSocketServer;

import java.util.List;

/**
 * @author huazi
 * @date 2021/12/3 17:05
 */
@RestController
@RequestMapping("/ws")
public class WebSocketController {

    @GetMapping("/list")
    public ResponseEntity<List<String>> list() {
        return ResponseEntity.ok(WebSocketServer.getIds());
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getUserCount() {
        return ResponseEntity.ok(WebSocketServer.getUserCount());
    }

    @GetMapping("/push/{message}")
    public ResponseEntity<String> push(@PathVariable(name = "message") String message) {
        WebSocketServer.batchSendInfo(message);
        return ResponseEntity.ok("WebSocket 推送消息给所有人");
    }

    @GetMapping("/pushTag/{userId}/{message}")
    public ResponseEntity<String> pushTag(@PathVariable(name = "userId") String userId,
                                          @PathVariable(name = "message") String message) {
        WebSocketServer.sendInfo(userId, message);
        return ResponseEntity.ok("WebSocket 推送消息给：" + userId);
    }

}
