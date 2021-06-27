package com.heller.climaApp.models

import androidx.lifecycle.MutableLiveData
import com.heller.climaApp.apiServices.LocationBean
import com.heller.climaApp.apiServices.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository {

    fun getLocations(str: String): MutableLiveData<List<LocationBean>>{

        val resp = MutableLiveData<List<LocationBean>>()

        RetrofitClient.apiServices.getLocations(str)
            .enqueue(object :Callback<List<LocationBean>>{
                override fun onResponse(
                    call: Call<List<LocationBean>>,
                    response: Response<List<LocationBean>>
                ) {
                    if(response.isSuccessful){
                        resp.postValue(response.body())
                    }else{
                        resp.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<LocationBean>>, t: Throwable) {
                    resp.postValue(null)
                }
            }
            )

        return resp
    }

    fun getDataLocation(woeid: String): MutableLiveData<WeatherBean>{

        val resp = MutableLiveData<WeatherBean>()

        RetrofitClient.apiServices.getDataLocation(woeid).enqueue(object :
        Callback<WeatherBean>{
            override fun onResponse(call: Call<WeatherBean>, response: Response<WeatherBean>) {
                if (response.isSuccessful){
                    resp.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<WeatherBean>, t: Throwable) {

            }
        })

        return resp
    }
}