package com.example.todos.service.todo

import com.example.todos.dto.todo.TodoDTO
import com.example.todos.model.Todo
import com.example.todos.model.User
import com.example.todos.service.user.UserMapper
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoMapper(val userMapper: UserMapper) {

    fun toDTO(todo: Todo) : TodoDTO = TodoDTO(
            todo.id,
            todo.name,
            todo.note,
            todo.priority,
            todo.deadline,
            todo.user?.let { userMapper.toUserDetailsDTO(it) }
    )

    fun fromCreateDTO(todoDTO: TodoDTO, user: User?) : Todo = Todo(
            name = todoDTO.name,
            note = todoDTO.note,
            priority = todoDTO.priority,
            deadline = todoDTO.deadline,
            user = user
    )

    fun fromUpdateDTO(todoDTO: TodoDTO, id: UUID) : Todo = Todo(
            id = id,
            name = todoDTO.name,
            note = todoDTO.note,
            priority = todoDTO.priority,
            deadline = todoDTO.deadline,
            user = todoDTO.user?.let { userMapper.fromUserDetailsDTO(it) }
    )
}
