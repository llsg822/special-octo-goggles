package com.example.product_manager.repositorty

import com.example.product_manager.entity.GtinProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface GtinProductRepository: JpaRepository<GtinProduct, String> {
    @Query("SELECT gp FROM GtinProduct gp LEFT JOIN FETCH Product p WHERE gp.state = 0 AND p.id IS NULL")
    fun findToBeSynchronized(): List<GtinProduct>
}