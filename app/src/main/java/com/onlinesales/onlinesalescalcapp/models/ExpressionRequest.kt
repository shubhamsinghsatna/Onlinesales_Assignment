package com.onlinesales.onlinesalescalcapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExpressionRequest(
    @Json(name = "expr") val expr: List<String>,
    @Json(name = "precision") val precision: Int? = null



)

//java.lang.IllegalArgumentException: Unable to create @Body converter for class com.onlinesales.onlinesalescalcapp.api.ExpressionRequest (parameter #1)
//for method ExpressionApiService.evaluateExpressions