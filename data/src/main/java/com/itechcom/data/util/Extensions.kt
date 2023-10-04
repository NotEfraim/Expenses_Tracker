package com.itechcom.data.util

import kotlin.reflect.KProperty1

fun <T : Any> T.toMap(): Map<String, Any?> {
    // Use reflection to get the properties of the data class
    val propertiesByName = this::class.members
        .filterIsInstance<KProperty1<T, *>>()
        .associateBy { it.name }

    // Create a map by iterating through the properties and their values
    return propertiesByName.keys.associateWith { propertyName ->
        val property = propertiesByName[propertyName]
        val value = property?.get(this)
        value
    }
}