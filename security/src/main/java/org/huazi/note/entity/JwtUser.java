package org.huazi.note.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * TODO 需要放在entity下？
 *
 * @author huazi
 * @date 2021/12/7 17:23
 */
public class JwtUser implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    //通过user创建
    public JwtUser(SysUser sysUser) {
        id = sysUser.getId();
        username = sysUser.getUsername();
        password = sysUser.getPassword();
        authorities = Collections.singleton(new SimpleGrantedAuthority(sysUser.getRole()));
    }

    // 获取权限信息，TODO 暂时只存角色
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号是否未过期
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 账号是否未锁定
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
