package com.example.product_manager.entity

import jakarta.persistence.*
import java.util.*

@Entity
class Product(
        @Id
        val id: UUID = UUID.randomUUID(),
        var name: String? = null,
        @ManyToOne(cascade = [CascadeType.PERSIST])
        var company: Company? = null,
        var standard: String? = null,
        var imageUrl: String? = null,
        @ManyToOne(cascade = [CascadeType.PERSIST])
        var category: Category? = null,
        var taxation: Boolean? = null,
) {
        companion object
}