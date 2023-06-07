package com.example.product_manager.controller

import com.example.product_manager.common.dto.ApiResponse
import com.example.product_manager.common.dto.ApiResponseCode
import com.example.product_manager.controller.dto.CategoryMappingRequest
import com.example.product_manager.module.ObjectExcelMapper
import com.example.product_manager.service.CategoryMappingService
import com.example.product_manager.service.GtinProductQueryService
import com.example.product_manager.service.GtinProductRegistrationService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(
        val gtinProductRegistrationService: GtinProductRegistrationService,
        val productQueryService: GtinProductQueryService,
        val objectExcelMapper: ObjectExcelMapper,
        val categoryMappingService: CategoryMappingService
) {
    @GetMapping("/products/excel")
    fun getProduct(response: HttpServletResponse) {
        val gtinProductResponseList = productQueryService.getProduct()
        val workbook = objectExcelMapper.map(gtinProductResponseList)

        response.contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        response.setHeader("Content-Disposition", "attachment; filename=${System.currentTimeMillis()}.xlsx")
        workbook.write(response.outputStream)
        workbook.close()
    }

    @PostMapping("/products/gtin")
    fun registerGtin(
        @RequestBody gtinList: List<String>
    ): ApiResponse<Nothing> {
        gtinProductRegistrationService.registerGtin(gtinList)
        return ApiResponse(
            ApiResponseCode.OK,
            null
        )
    }

    @PostMapping("/products/category")
    fun registerCategoryMapping(
            @RequestBody categoryMappingList: List<CategoryMappingRequest>
    ): ApiResponse<Nothing> {
        categoryMappingService.registerCategoryMapping(categoryMappingList)
        return ApiResponse(
                ApiResponseCode.OK,
                null
        )
    }
}