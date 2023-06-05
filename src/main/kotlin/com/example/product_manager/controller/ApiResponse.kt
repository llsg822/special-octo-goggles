package com.example.product_manager.controller

data class ApiResponse<T>(
    val code: ApiResponseCode,
    val data: T?
) {
    fun getMessage(): String {
        return code.name;
    }
}
