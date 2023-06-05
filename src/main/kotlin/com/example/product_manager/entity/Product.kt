package com.example.product_manager.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
class Product(
    @Id
    val id: UUID,
    var name: String
)