package com.onlinesales.onlinesalescalcapp.api

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onlinesales.onlinesalescalcapp.models.ExpressionRequest
import com.onlinesales.onlinesalescalcapp.models.ExpressionResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MathViewModel : ViewModel() {

    private val mathApi = Retrofit.Builder()
        .baseUrl("https://api.mathjs.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MathApiService::class.java)

    val results: MutableState<List<String>> = mutableStateOf(emptyList())
    var error: String = ""

    fun evaluateExpressions(input: String) {
        val expressions = input.lines().filter { it.isNotBlank() }

        val request = ExpressionRequest(expr = expressions)

        viewModelScope.launch {
            val response = mathApi.evaluateExpressions(request)

            response.enqueue(object : Callback<ExpressionResponse> {
                override fun onResponse(
                    call: Call<ExpressionResponse>,
                    response: Response<ExpressionResponse>
                ) {
                    if (response.isSuccessful) {
                        results.value = response.body()?.result ?: emptyList()
                        Log.e("results", results.value.toString())
                    } else {
                        val errorBody = response.errorBody()?.string()
                        error = errorBody.toString()
                        Log.e("errorBody", errorBody.toString())
                    }
                }

                override fun onFailure(call: Call<ExpressionResponse>, t: Throwable) {
                    Log.e("onFailure", t.message.toString())
                }
            })
        }
    }
}
