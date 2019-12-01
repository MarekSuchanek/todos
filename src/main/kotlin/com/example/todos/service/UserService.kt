package com.example.todos.service

import com.example.todos.dto.UserDTO
import com.example.todos.dto.UserDetailsDTO
import com.example.todos.model.User
import com.example.todos.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.security.core.authority.SimpleGrantedAuthority


@Service
class UserService(
        val userRepository: UserRepository,
        val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = findUserByEmail(email) ?: throw UsernameNotFoundException("User with email $email not found!")
        val authority = SimpleGrantedAuthority("ADMIN")
        return org.springframework.security.core.userdetails.User(user.email, user.password, listOf(authority))
    }

    fun getAllUsers(): List<UserDetailsDTO> = userRepository.findAll().map { UserDetailsDTO(it.uuid, it.email) }

    fun createNewUser(user: UserDTO): UserDetailsDTO {
        val newUser = User(email = user.email, password = passwordEncoder.encode(user.password))
        val savedUser = userRepository.save(newUser)
        return UserDetailsDTO(uuid = savedUser.uuid, email = savedUser.email)
    }

    fun findUserByEmail(email: String): User? = userRepository.findByEmail(email)
}
