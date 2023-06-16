package com.example.product_manager.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Company(
        @Id
        val id: String, // 사업자 등록번호
        val name: String?,
)
