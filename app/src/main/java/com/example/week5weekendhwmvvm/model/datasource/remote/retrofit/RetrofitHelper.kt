package com.example.week5weekendhwmvvm.model.datasource.remote.retrofit

import com.example.week5weekendhwmvvm.model.datasource.remote.retrofit.CONSTANTS.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    fun getRetrofitInstance() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}