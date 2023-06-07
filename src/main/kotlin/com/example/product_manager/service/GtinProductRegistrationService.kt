package com.example.product_manager.service

import com.example.product_manager.entity.GtinProduct
import com.example.product_manager.entity.GtinProductSyncState
import com.example.product_manager.repositorty.GtinProductRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class GtinProductRegistrationService(
        val gtinProductRepository: GtinProductRepository
) {
    fun registerGtin(gtinList: List<String>) {
        val alreadyExistGtinSet = gtinProductRepository.findAllById(gtinList)
                .map { gtinProduct -> gtinProduct.gtin }
                .toSet()
        val newGtinProductList = gtinList
                .filter { gtin -> !alreadyExistGtinSet.contains(gtin) }
                .map { gtin -> GtinProduct(gtin = gtin) }
        gtinProductRepository.saveAll(newGtinProductList)
    }
}