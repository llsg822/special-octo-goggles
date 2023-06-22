package com.example.product_manager.service

import com.example.product_manager.controller.dto.CategoryMappingRequest
import com.example.product_manager.entity.Category
import com.example.product_manager.repositorty.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class CategoryMappingService(
        val categoryRepository: CategoryRepository
) {
    fun registerCategoryMapping(categoryMappingList: List<CategoryMappingRequest>) {
        val categoryMappingRequestMap = categoryMappingList.associateBy({ it.categoryId }, { it })
        val categoryMap = categoryRepository.findAllById(categoryMappingRequestMap.keys).associateBy({ it.id }, { it })

        categoryMappingRequestMap.forEach { request ->
            categoryMap[request.key]?.let { category ->
                category.depth1Name = request.value.depth1Name
                category.depth2Name = request.value.depth2Name
                category.depth3Name = request.value.depth3Name
                category.depth4Name = request.value.depth4Name
                category.mappedName = request.value.mappedName
            } ?: run {
                val newCategory = Category(
                        id = request.key,
                        depth1Name = request.value.depth1Name,
                        depth2Name = request.value.depth2Name,
                        depth3Name = request.value.depth3Name,
                        depth4Name = request.value.depth4Name,
                        mappedName = request.value.mappedName,
                )
                categoryRepository.save(newCategory)
            }
        }
    }
}