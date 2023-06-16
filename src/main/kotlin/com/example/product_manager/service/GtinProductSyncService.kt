package com.example.product_manager.service

import com.example.product_manager.entity.Category
import com.example.product_manager.entity.Company
import com.example.product_manager.entity.Product
import com.example.product_manager.repositorty.CategoryRepository
import com.example.product_manager.repositorty.CompanyRepository
import com.example.product_manager.repositorty.GtinProductQueryRepository
import com.example.product_manager.service.dto.KoreanNetResponse
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Transactional
@Service
class GtinProductSyncService(
        val koreanNetService: KoreanNetService,
        val gtinProductQueryRepository: GtinProductQueryRepository,
        val categoryRepository: CategoryRepository,
        val companyRepository: CompanyRepository,
) {
    @Scheduled(fixedDelay = 300)
    fun synchronizeGtinProduct() {
        val gtinProduct = gtinProductQueryRepository.findOneToBeSynchronized() ?: return
        val koreanNetResponse = koreanNetService.getProduct(gtinProduct.gtin)
        when (koreanNetResponse.status) {
            "X" -> {
                gtinProduct.failToSynchronize()
                return
            }
            "Y" -> {}
            else -> throw IllegalArgumentException()
        }
        val category = koreanNetResponse.kanclasscode?.let {
            categoryRepository.findByIdOrNull(it) ?: run { Category(id = it) }
        }
        val company = koreanNetResponse.kbn?.let {
            companyRepository.findByIdOrNull(it) ?: run {
                Company(
                        id = it,
                        name = koreanNetResponse.conamek,
                )
            }
        }
        val newProduct = Product.of(
                koreanNetResponse = koreanNetResponse,
                category = category,
                company = company
        )
        gtinProduct.successToSynchronize(product = newProduct)
    }
}

fun Product.Companion.of(
        koreanNetResponse: KoreanNetResponse,
        company: Company?,
        category: Category?,
): Product {
    val name = koreanNetResponse.dscrgtink ?: koreanNetResponse.npname
    val standard = "${koreanNetResponse.netweight}${koreanNetResponse.netweightuom}"
    val taxation = when (koreanNetResponse.stTaxtype?.taxtype) {
        "과세" -> true
        "영세" -> false
        "면세" -> false
        else -> throw IllegalArgumentException(koreanNetResponse.stTaxtype?.taxtype)
    }
    return Product(
            name = name,
            company = company,
            standard = standard,
            imageUrl = koreanNetResponse.imgpath1,
            category = category,
            taxation = taxation,
    )
}