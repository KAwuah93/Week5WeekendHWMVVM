package com.example.week5weekendhwmvvm.viewmodel

import android.util.Log
import androidx.databinding.BaseObservable

import androidx.databinding.PropertyChangeRegistry
import androidx.fragment.app.FragmentManager
import com.example.week5weekendhwmvvm.model.Forecast.ForecastResponse
import com.example.week5weekendhwmvvm.model.Forecast.LocalForecastResponse
import com.example.week5weekendhwmvvm.model.datasource.remote.retrofit.CONSTANTS.API_KEY
import com.example.week5weekendhwmvvm.model.datasource.remote.retrofit.CONSTANTS.API_KEY2
import com.example.week5weekendhwmvvm.model.datasource.remote.retrofit.ForecastService
import com.example.week5weekendhwmvvm.view.fragments.MainForecastFragment
import com.example.week5weekendhwmvvm.view.fragments.SecondaryScrollFragment
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserMainActivityViewModel : BaseObservable {

    //using late init instead of just leaving these blank, wish me luck
    lateinit var propertyChangeRegistry : PropertyChangeRegistry

    //cut fragment bois
    //Fragment boys
    lateinit var mainForecastFragment: MainForecastFragment
    lateinit var secondaryScrollFragment: SecondaryScrollFragment
    lateinit var fragmentManager: FragmentManager

    //Variables for the view
    var primaryFragHeader = "Double check the neck boys, we in here"
    var primLocation = "location"
    var primeTemp = "00"
    var primeCondition = "Casual"
    var primeLowTemp = "00"
    var primeHighTemp = "00"

    constructor(propertyChangeRegistry: PropertyChangeRegistry) : super() {
        this.propertyChangeRegistry = propertyChangeRegistry
    }

    constructor()

    fun ForecastCall(zip: String){
        Log.d("NETWORK","STEP 1")

        ForecastService.createService()
            .getHourlyForecast(zip, API_KEY2)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                object : Observer<ForecastResponse>{
                    lateinit var forecastResponse: ForecastResponse
                    override fun onComplete() {
                        //TODO do the call and log the results
                        Log.d("NETWORK","STEP4")
                        Log.d("NETWORK", "Object = " + forecastResponse)

                    }
                    override fun onSubscribe(d: Disposable) {
                        Log.d("NETWORK","STEP 2 " + d)
                    }
                    override fun onNext(t: ForecastResponse) {
                        Log.d("NETWORK","STEP 3")
                        this.forecastResponse = t
                    }
                    override fun onError(e: Throwable) {
                        Log.e("NETWORK","STEP E", e)
                    }
                }
            )

    }

    fun currentCast(zip: String){

        ForecastService.createService()
            .getCurrentForecast(zip, API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                object : Observer<LocalForecastResponse>{
                    lateinit var localForecastResponse: LocalForecastResponse
                    override fun onComplete() {
                        //TODO do the call and log the results
                        Log.d("NETWORK", "Object = " + localForecastResponse)
                        setPrimeFrag(localForecastResponse)
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: LocalForecastResponse) {
                        this.localForecastResponse = t
                    }

                    override fun onError(e: Throwable) {
                    }
                }
            )

    }

    fun initialize() {
        //setPrimeFrag()

    }

    //@BindingAdapter("android:text")
    fun setPrimeFrag(localForecastResponse: LocalForecastResponse){
        // todo setting up everything to go through

        //assuring it isn't null so we can do the thing
        primLocation = localForecastResponse.name!!
        primeTemp = ""+ convertK(localForecastResponse.main?.temp!!)
        primeCondition = localForecastResponse.weather?.get(0)?.description!!.capitalize()

        //setting the highs and the lows
        primeHighTemp = ""+ convertK(localForecastResponse.main.tempMax!!)
        primeLowTemp = ""+ convertK(localForecastResponse.main.tempMin!!)

        //Notifying the viewModel of the change for the update
        notifyChange()
    }

    //Converting to the units we need
    fun convertK(K : Double) : Double{
        return Math.floor((K - 273.15) * (9.0/5.0) + 32)
    }
}