package org.huazi.note.model;

import lombok.Data;

/**
 * @author huazi
 * @date 2021/12/7 17:43
 */
@Data
public class LoginUser {
    private String username;
    private String password;
    private Integer rememberMe;
}
