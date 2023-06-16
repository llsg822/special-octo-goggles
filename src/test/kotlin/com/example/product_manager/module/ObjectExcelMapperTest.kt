package com.example.product_manager.module

import com.example.product_manager.service.dto.GtinProductResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.reflect.Field

class ObjectExcelMapperTest {
    private val objectExcelMapper = ObjectExcelMapper()

    private fun <T> getFieldsWithoutCompanionObject(clazz: Class<T>): List<Field> {
        return clazz.declaredFields.filter {
            !it.name.equals("Companion")
        }
//        return clazz.declaredFields.filter { !it.isSynthetic }.toTypedArray()
    }
     @Test
     fun a() {
         getFieldsWithoutCompanionObject(GtinProductResponse::class.java).forEach { println(it) }
     }
}