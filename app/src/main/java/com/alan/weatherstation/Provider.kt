package com.alan.weatherstation


object Provider {


    fun init() {
    }
    private val weatherModel: WeatherModel by lazy { WeatherModel() }

    val weatherViewModel: WeatherViewModel by lazy { WeatherViewModel(weatherModel) }


}