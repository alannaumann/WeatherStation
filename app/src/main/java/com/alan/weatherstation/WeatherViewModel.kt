package com.alan.weatherstation

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import java.util.*

class WeatherViewModel(private val weatherModel: WeatherModel) : Observer, BaseObservable() {


    init {
        weatherModel.addObserver(this)
    }

    override fun update(o: Observable?, arg: Any?) {
        when (arg as String) {
            "minTemp" -> notifyPropertyChanged(BR.minTemp)
            "feelTemp" -> notifyPropertyChanged(BR.feelTemp)
            "maxTemp" -> notifyPropertyChanged(BR.maxTemp)
            "temp" -> notifyPropertyChanged(BR.temp)
            "humidity" -> notifyPropertyChanged(BR.humitiy)
            "pressure" -> notifyPropertyChanged(BR.pressure)


        }
    }

    val minTemp: String
        @Bindable get() {
            return "${weatherModel.minTemp} °C"
        }

    val feelTemp: String
        @Bindable get() {
            return "${weatherModel.feelTemp} °C"
        }

    val maxTemp: String
        @Bindable get() {
            return "${weatherModel.maxTemp} °C"
        }

    val temp:String
        @Bindable get() {
            return "${weatherModel.temp} °C"
        }

    val humitiy:String
        @Bindable get() {
            return "${weatherModel.humidity} °C"
        }
    val pressure:String
        @Bindable get() {
            return "${weatherModel.pressure} °C"
        }
    
}