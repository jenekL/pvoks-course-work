package com.uni.pvoks.service;

import com.uni.pvoks.model.User;
import com.uni.pvoks.repository.UserRepository;
import com.uni.pvoks.rest.dto.UserInfo;
import com.uni.pvoks.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("User with id %d does not exist...", id)));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("User with email %s does not exist...", email)));
    }

    @Override
    public User save(UserInfo userInfo) {
        User user = new User();
        user.setEmail(userInfo.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User update(long id, UserInfo userInfo) {
        User existingUser = findById(id);
        existingUser.setEmail(userInfo.getEmail());
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(long id) {
        User existingUser = findById(id);
        userRepository.delete(existingUser);
    }
}
