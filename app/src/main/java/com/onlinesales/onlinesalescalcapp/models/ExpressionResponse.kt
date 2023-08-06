package com.onlinesales.onlinesalescalcapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExpressionResponse(
    @Json(name = "result") val result: List<String>,
    @Json(name = "error") val error: String? = null
)
