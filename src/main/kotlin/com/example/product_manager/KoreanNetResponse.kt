package com.example.product_manager

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "mgs1OutXml")
data class KoreanNetResponse(
        val gtin: String
)
