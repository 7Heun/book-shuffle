package com.bookshuffle.controller;

import com.bookshuffle.domain.User;
import com.bookshuffle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users/new")
    public String create(@RequestBody User user) {
        userService.join(user);
        return "유저 저장 성공";
    }
}