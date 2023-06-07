package com.example.product_manager.module

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ObjectExcelMapperTest {
    private val objectExcelMapper = ObjectExcelMapper()

    data class A (
        val a: Int,
        val b: String,
        val c: Boolean,
    )
    @Test
    fun mappingTest() {
        // given
        val test1 = A(1, "1", true)
        val test2 = A(2, "2", false)
        val testList = listOf(test1, test2)

        // when
        val workbook = objectExcelMapper.map(testList)
        val sheet = workbook.getSheetAt(0)
        // then
        assertEquals("1", sheet.getRow(1).getCell(0).rawValue)
        assertEquals("2", sheet.getRow(1).getCell(0).rawValue)
    }
}