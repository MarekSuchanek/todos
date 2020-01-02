package com.example.todos.dto.user

import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class UserRegistrationDTO(
        val firstname: String,
        val lastname: String,

        @field: Email
        val email: String,

        @field: Size(min=7, max=10)
        val password: String
)