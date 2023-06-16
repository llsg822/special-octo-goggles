package com.example.product_manager.controller.dto

data class CategoryMappingRequest(
        val categoryId: String,
        val depth1Name: String,
        val depth2Name: String,
        val depth3Name: String,
        val depth4Name: String,
)
