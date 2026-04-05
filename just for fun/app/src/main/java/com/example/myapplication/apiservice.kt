package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

data class KanyeResponse(val quote: String)

interface ApiService {
    @GET("/")
    fun getQuote(): Call<KanyeResponse>
}