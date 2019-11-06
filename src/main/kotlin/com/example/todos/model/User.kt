package com.example.todos.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity(name="XUser")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val uuid: UUID = UUID.randomUUID(),

        @Email
        @Column(unique = true)
        @get: NotBlank
        val email: String = "",

        @get: JsonIgnore
        val password: String = ""
) : Serializable
