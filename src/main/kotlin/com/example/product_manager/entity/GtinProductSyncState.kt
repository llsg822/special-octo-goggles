package com.example.product_manager.entity

enum class GtinProductSyncState {
    PENDING, // 연동 대기 GTIN
    SUCCESS, // 연동 성공 GTIN
    MANUAL, // 연동 실패, 수동 관리 필요
}