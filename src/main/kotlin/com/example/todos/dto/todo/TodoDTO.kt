package com.example.todos.dto.todo

import com.example.todos.dto.user.UserDetailsDTO
import com.example.todos.model.User
import java.sql.Timestamp
import java.util.*

data class TodoDTO(
        val id: UUID? = null,
        val name: String,
        val note: String?,
        val priority: Int,
        val deadline: Timestamp?,
        val user: UserDetailsDTO? = null
)
