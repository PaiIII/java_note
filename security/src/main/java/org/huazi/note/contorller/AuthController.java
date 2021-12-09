package org.huazi.note.contorller;

import lombok.RequiredArgsConstructor;
import org.huazi.note.entity.SysUser;
import org.huazi.note.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author huazi
 * @date 2021/12/7 18:46
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String, String> registerUser) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(registerUser.get("username"));
        sysUser.setPassword(bCryptPasswordEncoder.encode(registerUser.get("password")));
        sysUser.setRole("ROLE_USER");
        SysUser save = userRepository.save(sysUser);
        return save.toString();
    }
}
