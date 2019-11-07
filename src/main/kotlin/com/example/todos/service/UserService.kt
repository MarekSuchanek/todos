package com.example.todos.service

import com.example.todos.dto.UserDTO
import com.example.todos.dto.UserDetailsDTO
import com.example.todos.model.User
import com.example.todos.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) {

    fun getAllUsers(): List<UserDetailsDTO> = userRepository.findAll().map { UserDetailsDTO(it.uuid, it.email) }

    fun createNewUser(user: UserDTO): UserDetailsDTO {
        print("User.password = ${user.password}")
        val newUser = User(email = user.email, password = passwordEncoder.encode(user.password))
        val savedUser = userRepository.save(newUser)
        return UserDetailsDTO(uuid = savedUser.uuid, email = savedUser.email)
    }

    fun authenticateUser(email: String, rawPassword: String): Boolean {
        val user = findUserByEmail(email)
        return (user != null) and passwordEncoder.matches(rawPassword, user?.password)
    }

    fun findUserByEmail(email: String): User? = userRepository.findByEmail(email)
}