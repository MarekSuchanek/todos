package com.example.todos.model

import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity(name = "XUser")
data class User(
        @field: Id
        @field: GeneratedValue(strategy = GenerationType.AUTO)
        val id: UUID = UUID.randomUUID(),

        val firstname: String = "",
        val lastname: String = "",


        @field: Column(unique = true)
        @get: NotBlank
        val email: String = "",

        val password: String = "",

        @OneToMany(mappedBy = "user")
        val todos: List<Todo>? = null
) : Serializable
