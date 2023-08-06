package com.onlinesales.onlinesalescalcapp.api

import com.onlinesales.onlinesalescalcapp.models.ExpressionRequest
import com.onlinesales.onlinesalescalcapp.models.ExpressionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MathApiService {

    @Headers("Content-Type: application/json")
    @POST("v4")
    fun evaluateExpressions(@Body request: ExpressionRequest): Call<ExpressionResponse>
}