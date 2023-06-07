package com.example.product_manager.repositorty

import com.example.product_manager.entity.GtinProduct
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class GtinProductQueryRepository(
        private val entityManager: EntityManager
) {
    fun findOneToBeSynchronized(): GtinProduct? {
        val query = entityManager
                .createQuery(
                        "SELECT gp FROM GtinProduct gp WHERE gp.state = 0 AND gp.product.id IS NULL",
                        GtinProduct::class.java
                )
                .setMaxResults(1)
        return query.resultList.firstOrNull()
    }
}