package com.alan.weatherstation

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.extensions.cUrlString
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.FuelJson
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import timber.log.Timber
import java.util.*

const val API_CALL =
    "https://api.openweathermap.org/data/2.5/weather?zip=85586,de&units=metric&appid=ce1ee4c77776617c2bcc03d2658c1641"

class WeatherModel : Observable() {

    var feelTemp: String = "0"
        set(value) {
            field = value
            setChangedAndNotify("feelTemp")
        }

    var minTemp: String = "0"
        set(value) {
            field = value
            setChangedAndNotify("minTemp")
        }

    var maxTemp: String = "0"
        set(value) {
            field = value
            setChangedAndNotify("maxTemp")
        }

    var temp: String = "0"
        set(value) {
            field = value
            setChangedAndNotify("temp")
        }

    var humidity: String = "0"
        set(value) {
            field = value
            setChangedAndNotify("humidity")
        }

    var pressure: String = "0"
        set(value) {
            field = value
            setChangedAndNotify("pressure")
        }


    fun request() {
        val httpAsync =
            API_CALL
                .httpGet()
                .also { Timber.d("blubb %s", it.cUrlString()) }
                .responseJson { _, _, result ->
                    update(result)
                }
        httpAsync.join()
    }

    fun update(result: Result<FuelJson, FuelError>) {
        val main = result.get().obj().getJSONObject("main")

        feelTemp = main.get("feels_like").toString()
        temp = main.get("temp").toString()
        maxTemp = main.get("temp_max").toString()
        minTemp = main.get("temp_min").toString()
        pressure = main.get("pressure").toString()
        humidity = main.get("humidity").toString()

    }

    private fun setChangedAndNotify(field: Any) {
        setChanged()
        notifyObservers(field)
    }
}