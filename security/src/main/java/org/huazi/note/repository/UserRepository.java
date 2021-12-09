package org.huazi.note.repository;

import org.huazi.note.entity.SysUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author huazi
 * @date 2021/12/7 17:17
 */
public interface UserRepository extends CrudRepository<SysUser, Integer> {

    SysUser findByUsername(String username);
}
