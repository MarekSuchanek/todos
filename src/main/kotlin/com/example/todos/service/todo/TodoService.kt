package com.example.todos.service.todo

import com.example.todos.dto.todo.TodoDTO
import com.example.todos.repository.TodoRepository
import com.example.todos.service.user.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoService(
        val todoRepository: TodoRepository,
        val userService: UserService,
        val todoMapper: TodoMapper
) {
    fun getAllTodos() : List<TodoDTO> = todoRepository.findAll().map { todoMapper.toDTO(it) }

    fun createNewTodo(todoDTO: TodoDTO): TodoDTO = todoMapper.toDTO(todoRepository.save(todoMapper.fromCreateDTO(todoDTO, userService.getCurrentUser())))

    fun findById(id: UUID): TodoDTO? = todoRepository.findByIdOrNull(id)?.let { todoMapper.toDTO(it) }

    fun delete(id: UUID): Boolean {
        val exists = todoRepository.existsById(id)
        todoRepository.deleteById(id)
        return exists
    }

    fun update(todoDTO: TodoDTO, id: UUID): TodoDTO? = todoMapper.toDTO(todoRepository.save(todoMapper.fromUpdateDTO(todoDTO, id)))

}
