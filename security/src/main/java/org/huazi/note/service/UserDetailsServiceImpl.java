package org.huazi.note.service;

import lombok.RequiredArgsConstructor;
import org.huazi.note.entity.JwtUser;
import org.huazi.note.entity.SysUser;
import org.huazi.note.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author huazi
 * @date 2021/12/7 17:20
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser sysUser = userRepository.findByUsername(username);
        return new JwtUser(sysUser);
    }
}
