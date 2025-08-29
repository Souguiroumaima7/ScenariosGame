package com.example.securetasks.model


import kotlinx.serialization.Serializable


@Serializable
data class Task(
val id: String, // UUIDv4
val title: String, // validated, max length
val notes: String? = null, // optional, capped
val createdAt: Long, // epoch millis
val done: Boolean = false
)
