package com.example.product_manager.service.dto

import com.example.product_manager.entity.GtinProduct
import com.example.product_manager.entity.GtinProductSyncState
import java.time.LocalDateTime

data class GtinProductResponse(
        val gtin: String? = null,
        val name: String? = null,
        val company: String? = null,
        val imageUrl: String? = null,
        val matchedCategoryName: String? = null,
        val taxation: Boolean? = null,
        val syncState: GtinProductSyncState,
        val synchronizedAt: LocalDateTime? = null
) {
    companion object {
        fun of(gtinProduct: GtinProduct): GtinProductResponse {
            return GtinProductResponse(
                    gtin = gtinProduct.gtin,
                    name = gtinProduct.product?.name,
                    company = gtinProduct.product?.company?.name,
                    imageUrl = gtinProduct.product?.imageUrl,
                    matchedCategoryName = gtinProduct.product?.category?.mappedName,
                    taxation = gtinProduct.product?.taxation,
                    syncState = gtinProduct.state,
                    synchronizedAt = gtinProduct.synchronizedAt,
            )
        }
    }
}