package com.uni.pvoks.service.api;

import com.uni.pvoks.model.User;
import com.uni.pvoks.rest.dto.UserInfo;

public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    User save(UserInfo userInfo);

    User update(long id, UserInfo userInfo);

    void delete(long id);
}
