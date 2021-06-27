package com.heller.climaApp.apiServices

import com.google.gson.annotations.SerializedName

data class LocationBean(
        val title: String,
        @SerializedName("location_type")
        val locationType: String,
        val woeid: String
)