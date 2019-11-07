package com.example.todos.controller

import com.example.todos.dto.AuthDTO
import com.example.todos.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class AuthController(val userService: UserService, val passwordEncoder: PasswordEncoder) {

    @PostMapping("/auth")
    fun auth(@Valid @RequestBody authDTO: AuthDTO): Boolean = userService.authenticateUser(authDTO.email, authDTO.password)
}