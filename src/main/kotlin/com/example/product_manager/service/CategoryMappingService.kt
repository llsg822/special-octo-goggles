package com.example.product_manager.service

import com.example.product_manager.controller.dto.CategoryMappingRequest
import com.example.product_manager.entity.Category
import com.example.product_manager.repositorty.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class CategoryMappingService (
    val categoryRepository: CategoryRepository
) {
    fun registerCategoryMapping(categoryMappingList: List<CategoryMappingRequest>) {
        val categoryMappingRequestMap = categoryMappingList.associateBy({ it.categoryId }, { it })
        val categoryMap = categoryRepository.findAllById(categoryMappingRequestMap.keys).associateBy({ it.id }, { it })

        categoryMappingRequestMap.forEach { request ->
            categoryMap[request.key]?.let { category ->
                category.name = request.value.name
            } ?: run {
                val newCategory = Category(
                        id = request.key,
                        name = request.value.name
                )
                categoryRepository.save(newCategory)
            }
        }
    }
}