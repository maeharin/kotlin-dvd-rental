package com.maeharin.kotlindvdrental.domain.model

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.CategoryEntity
import java.time.LocalDateTime

data class Category(
    val id: Int,
    val name: String,
    val updatedAt: LocalDateTime
)
