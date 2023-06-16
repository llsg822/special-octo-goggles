package com.example.product_manager.repositorty

import com.example.product_manager.entity.GtinProduct
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface GtinProductRepository : JpaRepository<GtinProduct, String> {
    @Query("""
        SELECT gp
        FROM GtinProduct gp
        JOIN FETCH gp.product
        JOIN FETCH gp.product.category
        JOIN FETCH gp.product.company
        """)
    fun findAllWithProduct(): List<GtinProduct>
}