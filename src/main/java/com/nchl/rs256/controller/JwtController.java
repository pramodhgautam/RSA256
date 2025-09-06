package com.nchl.rs256.controller;

import com.nchl.rs256.service.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/generate/{username}")
    public String generate(@PathVariable String username) {
        return jwtService.generateToken(username);
    }

    @PostMapping("/validate")
    public String validate(@RequestBody String token) {
        return jwtService.validateToken(token);
    }
}
