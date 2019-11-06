package com.example.todos.service

import com.example.todos.model.User
import com.example.todos.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) {

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun createNewUser(user: User): User {
        val passwordHash: String = passwordEncoder.encode(user.password)
        val newUser = User(email = user.email, password = passwordHash)
        return userRepository.save(newUser)
    }

    fun findUserByEmail(email: String): User? = userRepository.findByEmail(email)
}