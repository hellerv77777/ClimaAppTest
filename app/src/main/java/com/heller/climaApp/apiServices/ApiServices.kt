package com.heller.climaApp.apiServices

import com.heller.climaApp.models.WeatherBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET(SEARCH)
    fun getLocations(@Query(QUERY_PARAMS) string: String): Call<List<LocationBean>>

    @GET(DETAIL_SEARCH)
    fun getDataLocation(@Path(PATH) woeid: String): Call<WeatherBean>
}