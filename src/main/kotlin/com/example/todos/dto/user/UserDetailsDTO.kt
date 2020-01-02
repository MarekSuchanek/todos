package com.example.todos.dto.user

import java.util.*

data class UserDetailsDTO(
        val id: UUID,
        val firstname: String,
        val lastname: String,
        val email: String
)