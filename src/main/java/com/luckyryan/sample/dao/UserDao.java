package com.luckyryan.sample.dao;

import com.luckyryan.sample.dao.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * User: ryan
 * Date: 2/20/13
 */
public interface UserDao extends CrudRepository<UserEntity,Long> {
}
