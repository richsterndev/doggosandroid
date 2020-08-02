package com.richstern.doggos.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    val message: T?,
    val status: String?
)