package com.example.product_manager.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import java.time.LocalDateTime

@Entity
class GtinProduct(
        @Id
        val gtin: String,
        @OneToOne(cascade = [CascadeType.PERSIST])
        var product: Product? = null,
        var state: GtinProductSyncState = GtinProductSyncState.SYNC,
        var synchronizedAt: LocalDateTime? = null,
) {
    fun successToSynchronize(product: Product) {
        this.product = product;
        this.synchronizedAt = LocalDateTime.now();
    }

    fun failToSynchronize() {
        this.state = GtinProductSyncState.MANUAL;
    }
}