package com.heller.climaApp

fun roundDecimal(temp:Double): Double {
    return String.format("%.2f", temp).toDouble()
}

