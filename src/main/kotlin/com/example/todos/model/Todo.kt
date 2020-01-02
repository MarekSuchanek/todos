package com.example.todos.model

import java.io.Serializable
import java.sql.Timestamp
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity(name = "Todo")
data class Todo(
    @field: Id
    @field: GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @get: NotBlank
    val name: String = "",

    val note: String? = null,

    val priority: Int = 0,

    val deadline: Timestamp? = null,

    @field: ManyToOne
    @field: JoinColumn(name="user_id")
    val user: User? = null
) : Serializable
