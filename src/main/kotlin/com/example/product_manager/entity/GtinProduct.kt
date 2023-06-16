package com.example.product_manager.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class GtinProduct(
        @Id
        val gtin: String,
        @OneToOne(cascade = [CascadeType.PERSIST])
        var product: Product? = null,
        @Enumerated(value = EnumType.STRING)
        var state: GtinProductSyncState,
        var synchronizedAt: LocalDateTime? = null,
) {
    fun failToSynchronize() {
        this.state = GtinProductSyncState.MANUAL
        this.synchronizedAt = LocalDateTime.now()
    }
    fun successToSynchronize(product: Product) {
        this.product = product
        this.state = GtinProductSyncState.SUCCESS
        this.synchronizedAt = LocalDateTime.now()
    }
}