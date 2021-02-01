package com.uni.pvoks.rest;

import com.uni.pvoks.model.User;
import com.uni.pvoks.rest.dto.UserInfo;
import com.uni.pvoks.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<UserInfo> getUser(@PathVariable Long id) {
        User existingUser = userService.findById(id);
        return ResponseEntity.ok(mapToInfo(existingUser));
    }

    @PostMapping
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo userInfo) {
        User createdUser = userService.save(userInfo);
        return ResponseEntity.ok(mapToInfo(createdUser));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserInfo> updateUser(@PathVariable Long id,
                                               @RequestBody UserInfo userInfo) {
        User updatedUser = userService.update(id, userInfo);
        return ResponseEntity.ok(mapToInfo(updatedUser));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    private UserInfo mapToInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setEmail(user.getEmail());
        return userInfo;
    }
}
