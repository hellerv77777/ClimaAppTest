package com.heller.climaApp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heller.climaApp.apiServices.LocationBean
import com.heller.climaApp.models.AppRepository
import com.heller.climaApp.models.WeatherBean

class AppViewModel :ViewModel() {

    private val mRepository: AppRepository = AppRepository()


    fun getLocations(str:String) : MutableLiveData<List<LocationBean>>{
       return mRepository.getLocations(str);
    }

    fun getDataLocation(str:String) : MutableLiveData<WeatherBean>{
        return mRepository.getDataLocation(str);
    }
}