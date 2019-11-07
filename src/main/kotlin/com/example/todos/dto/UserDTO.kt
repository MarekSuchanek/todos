package com.example.todos.dto

import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class UserDTO(
        val uuid: UUID? = null,

        @field: Email
        val email: String,

        @field: Size(min=7, max=10)
        val password: String
)