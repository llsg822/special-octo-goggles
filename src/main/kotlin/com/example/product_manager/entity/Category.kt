package com.example.product_manager.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Category(
        @Id
        val id: String,
        var depth1Name: String? = null,
        var depth2Name: String? = null,
        var depth3Name: String? = null,
        var depth4Name: String? = null,
        var mappedName: String? = null,
)