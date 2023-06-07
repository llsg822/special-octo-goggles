package com.example.product_manager.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Category(
    @Id
    val id: String,
    var name: String?,
)