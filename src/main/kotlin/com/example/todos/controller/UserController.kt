package com.example.todos.controller

import com.example.todos.model.User
import com.example.todos.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class UserController(val userService: UserService) {

    @GetMapping("/users")
    fun getAllUsers(): List<User> = userService.getAllUsers()

    @PostMapping("/users")
    fun createNewUser(@Valid @RequestBody user: User): User = userService.createNewUser(user)
}