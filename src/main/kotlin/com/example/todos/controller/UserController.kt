package com.example.todos.controller

import com.example.todos.dto.user.UserRegistrationDTO
import com.example.todos.dto.user.UserDetailsDTO
import com.example.todos.model.User
import com.example.todos.service.user.UserService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @GetMapping
    fun getAllUsers(): List<UserDetailsDTO> = userService.getAllUsers()

    @GetMapping("/current")
    fun getCurrentUser(auth: Authentication): UserDetailsDTO? = userService.findUserByEmail(auth.name)

    @PostMapping("/register")
    fun createNewUser(@Valid @RequestBody user: UserRegistrationDTO): UserDetailsDTO = userService.createNewUser(user)
}
