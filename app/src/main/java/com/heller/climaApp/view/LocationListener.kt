package com.heller.climaApp.view

import com.heller.climaApp.apiServices.LocationBean

interface LocationListener {

    fun onClickLocation(obj: LocationBean)
}