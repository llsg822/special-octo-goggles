package com.example.product_manager.module

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component
import java.lang.reflect.Field

@Component
class ObjectExcelMapper {
    fun <T> map(dataList: List<T>): XSSFWorkbook {
        if (dataList.isEmpty()) throw IllegalArgumentException()

        val clazz = dataList[0]!!::class.java
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet()

        this.setHeaders(sheet, clazz)
        dataList.forEach { data ->
            appendRow(sheet, data)
        }

        return workbook
    }

    private fun <T> setHeaders(sheet: XSSFSheet, clazz: Class<T>) {
        val fields = getFieldsWithoutCompanionObject(clazz)
        val headerRow = sheet.createRow(0)
        for ((index, field) in fields.withIndex()) {
            field.isAccessible = true
            val cell = headerRow.createCell(index)
            cell.setCellValue(field.name)
        }
    }

    private fun <T> appendRow(sheet: XSSFSheet, element: T) {
        val fields = getFieldsWithoutCompanionObject(element!!::class.java)
        val dataRow = sheet.createRow(sheet.lastRowNum + 1)
        for ((index, field) in fields.withIndex()) {
            field.isAccessible = true
            val cell = dataRow.createCell(index)
            val value = field.get(element)?.toString() ?: ""
            cell.setCellValue(value)
        }
    }

    private fun <T> getFieldsWithoutCompanionObject(clazz: Class<T>): List<Field> {
        return clazz.declaredFields.filter {
            !it.name.equals("Companion")
        }
    }
}