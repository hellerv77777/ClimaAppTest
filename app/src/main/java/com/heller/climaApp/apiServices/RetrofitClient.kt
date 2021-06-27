package com.heller.climaApp.apiServices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val apiServices: ApiServices by lazy{
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        retrofit.create(ApiServices::class.java)
    }

}