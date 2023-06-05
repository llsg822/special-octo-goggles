package com.example.product_manager.controller

import com.example.product_manager.service.GtinProductRegistrationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    val gtinProductRegistrationService: GtinProductRegistrationService,
) {
    @PostMapping("/products/gtin")
    fun registerGtinProduct(
        @RequestBody gtinList: List<String>
    ): ApiResponse<Nothing> {
        gtinProductRegistrationService.registerGtin(gtinList)
        return ApiResponse(
            ApiResponseCode.OK,
            null
        )
    }
}