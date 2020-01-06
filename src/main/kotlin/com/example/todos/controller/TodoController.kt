package com.example.todos.controller

import com.example.todos.controller.exceptions.ResourceNotFoundException
import com.example.todos.dto.todo.TodoDTO
import com.example.todos.dto.todo.TodoPartialDTO
import com.example.todos.service.todo.TodoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/todos")
class TodoController(val todoService: TodoService) {

    @GetMapping
    fun getAllTodos(): List<TodoDTO> = todoService.getAllTodos()

    @PostMapping
    fun createTodo(@Valid @RequestBody todoDTO: TodoDTO): TodoDTO = todoService.createNewTodo(todoDTO)

    @GetMapping("/{id}")
    fun getTodo(@PathVariable("id") id: UUID): TodoDTO = todoService.findById(id) ?: throw ResourceNotFoundException()

    @PutMapping("/{id}")
    fun updateTodo(@PathVariable("id") id: UUID, @Valid @RequestBody todoDTO: TodoDTO): TodoDTO = todoService.update(todoDTO, id) ?: throw ResourceNotFoundException()

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun deleteTodo(@PathVariable("id") id: UUID) = if (todoService.delete(id)) null else throw ResourceNotFoundException()

    @PatchMapping("/{id}")
    fun patchTodo(@PathVariable("id") id: UUID, @Valid @RequestBody todoPartialDTO: TodoPartialDTO): TodoDTO = todoService.patch(todoPartialDTO, id) ?: throw ResourceNotFoundException()
}
