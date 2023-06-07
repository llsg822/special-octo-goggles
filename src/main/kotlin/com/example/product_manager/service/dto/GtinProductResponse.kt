package com.example.product_manager.service.dto

import com.example.product_manager.entity.GtinProductSyncState
import java.time.LocalDateTime

data class GtinProductResponse(
        val gtin: String?,
        val name: String?,
        val imageUrl: String?,
        val categoryId: String?,
        val categoryName: String?,
        val syncState: GtinProductSyncState,
        val synchronizedAt: LocalDateTime?
)