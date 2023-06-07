package com.example.product_manager.common.dto

data class ApiResponse<T>(
        val code: ApiResponseCode,
        val data: T?
)