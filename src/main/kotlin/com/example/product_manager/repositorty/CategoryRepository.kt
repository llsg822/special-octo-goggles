package com.example.product_manager.repositorty

import com.example.product_manager.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, String> {
}