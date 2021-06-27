package com.heller.climaApp.models

import com.heller.climaApp.models.ConsolidatedWeather
import com.heller.climaApp.models.Parent
import com.heller.climaApp.models.Source

data class WeatherBean(
    val consolidated_weather: List<ConsolidatedWeather>,
    val latt_long: String,
    val location_type: String,
    val parent: Parent,
    val sources: List<Source>,
    val sun_rise: String,
    val sun_set: String,
    val time: String,
    val timezone: String,
    val timezone_name: String,
    val title: String,
    val woeid: Int
)