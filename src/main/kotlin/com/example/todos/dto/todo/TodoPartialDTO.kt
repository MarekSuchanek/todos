package com.example.todos.dto.todo

import com.fasterxml.jackson.annotation.JsonInclude
import java.sql.Timestamp
import java.util.*

@JsonInclude(JsonInclude.Include.NON_ABSENT)
data class TodoPartialDTO(
        val name: Optional<String>,
        val note: Optional<String?>,
        val priority: Optional<Int>,
        val deadline: Optional<Timestamp?>
)