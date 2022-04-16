package me.brisson.apitest.data.model

data class CustomerResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val createdAt: Long
)
