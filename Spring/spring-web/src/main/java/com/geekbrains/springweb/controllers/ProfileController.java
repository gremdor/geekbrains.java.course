package com.geekbrains.springweb.controllers;

import com.geekbrains.springweb.dto.ProfileDto;
import com.geekbrains.springweb.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @GetMapping
    public ProfileDto getCurrentUserInfo(Principal principal) {
        // User user = userService.findByUsername(principal.getName());
        return new ProfileDto(principal.getName());
    }
}
