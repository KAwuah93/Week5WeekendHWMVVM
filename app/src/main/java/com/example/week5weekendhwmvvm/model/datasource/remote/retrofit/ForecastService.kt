package com.example.week5weekendhwmvvm.model.datasource.remote.retrofit

import com.example.week5weekendhwmvvm.model.Forecast.ForecastResponse
import com.example.week5weekendhwmvvm.model.Forecast.LocalForecastResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    companion object{
        fun createService() : ForecastService =
            RetrofitHelper().getRetrofitInstance().create(ForecastService::class.java)
    }

    //TODO hard code the second part of the query
    @GET("data/2.5/forecast")
    fun getHourlyForecast(@Query("zip")zipCode: String,@Query("APPID") KEY : String)
    :Observable<ForecastResponse>

    @GET("data/2.5/weather")
    fun getCurrentForecast(@Query("zip")zipCode: String,@Query("APPID") KEY : String)
    :Observable<LocalForecastResponse>
}