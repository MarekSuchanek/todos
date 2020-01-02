package com.example.todos.service.user

import com.example.todos.dto.user.UserDetailsDTO
import com.example.todos.dto.user.UserRegistrationDTO
import com.example.todos.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserMapper(
        val passwordEncoder: PasswordEncoder
) {

    fun toUserDetailsDTO(user: User): UserDetailsDTO = UserDetailsDTO(
            user.id,
            user.firstname,
            user.lastname,
            user.email
    )

    fun fromUserRegistrationDTO(userRegistrationDTO: UserRegistrationDTO): User = User(
            firstname = userRegistrationDTO.firstname,
            lastname = userRegistrationDTO.lastname,
            email = userRegistrationDTO.email,
            password = passwordEncoder.encode(userRegistrationDTO.password)
    )

    fun fromUserDetailsDTO(userDetailsDTO: UserDetailsDTO): User = User(
            id = userDetailsDTO.id,
            firstname = userDetailsDTO.firstname,
            lastname = userDetailsDTO.lastname,
            email = userDetailsDTO.email
    )
}
