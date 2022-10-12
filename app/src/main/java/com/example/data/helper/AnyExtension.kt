package com.example.data.helper

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.JsonNodeFactory

/**
 * Serialize object
 */
fun Any?.serialize(): String {
    return if (this == null) {
        ""
    } else {
        ObjectMapper()
            .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true))
            .writeValueAsString(this)
    }
}

/**
 * Deserialize object
 */
inline fun <reified T> String?.toObject(): T? {
    if (this.isNullOrEmpty()) {
        return null
    }
    return try {
        ObjectMapper()
            .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true))
            .readValue(this, object : TypeReference<T>() {})
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}