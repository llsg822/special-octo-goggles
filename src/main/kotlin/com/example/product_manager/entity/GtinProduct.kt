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
    var product: Product?,
    var state: GtinProductSyncState,
    var synchronizedAt: LocalDateTime?,
) {
}