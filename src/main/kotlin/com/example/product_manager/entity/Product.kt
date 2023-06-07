package com.example.product_manager.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.UUID

@Entity
class Product(
        @Id
        val id: UUID = UUID.randomUUID(),
        var name: String?,
        var imageUrl: String?,
        @ManyToOne(cascade = [CascadeType.PERSIST])
        var category: Category?
)