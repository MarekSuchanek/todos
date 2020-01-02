package com.example.todos.service.user

import com.example.todos.dto.user.UserDetailsDTO
import com.example.todos.dto.user.UserRegistrationDTO
import com.example.todos.model.User
import com.example.todos.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService(
        val userRepository: UserRepository,
        val userMapper: UserMapper
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email) ?: throw UsernameNotFoundException("User with email $email not found!")
        val authority = SimpleGrantedAuthority("ADMIN")
        return org.springframework.security.core.userdetails.User(user.email, user.password, listOf(authority))
    }

    fun getAllUsers(): List<UserDetailsDTO> = userRepository.findAll().map { userMapper.toUserDetailsDTO(it) }

    fun createNewUser(userRegistrationDTO: UserRegistrationDTO): UserDetailsDTO {
        val savedUser = userRepository.save(userMapper.fromUserRegistrationDTO(userRegistrationDTO))
        return userMapper.toUserDetailsDTO(savedUser)
    }

    fun findUserByEmail(email: String): UserDetailsDTO? = userRepository.findByEmail(email)?.let { userMapper.toUserDetailsDTO(it) }

    fun getCurrentUser(): User? {
        val username = SecurityContextHolder.getContext().authentication.principal
        return if (username is String) userRepository.findByEmail(username) else null
    }
}
