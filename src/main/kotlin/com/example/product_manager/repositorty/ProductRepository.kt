package com.example.product_manager.repositorty

import com.example.product_manager.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductRepository: JpaRepository<Product, UUID> {
}