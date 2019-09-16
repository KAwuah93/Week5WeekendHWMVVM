package com.example.week5weekendhwmvvm.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.week5weekendhwmvvm.R
import com.example.week5weekendhwmvvm.databinding.FragmentForecastMainBinding
import com.example.week5weekendhwmvvm.viewmodel.UserMainActivityViewModel
import android.util.Log
import android.content.SharedPreferences

class MainForecastFragment : Fragment{
    lateinit var mListener: onFragmentInteractionListener
    lateinit var binder : FragmentForecastMainBinding
    lateinit var viewModel: UserMainActivityViewModel

    lateinit var ZipCode : String
    lateinit var preferences : SharedPreferences

    //required empty constructor
    constructor()

    //getting a new instance of the MainForecastFragment
    fun newInstance() : MainForecastFragment{
        val fragment = MainForecastFragment()
        val args = Bundle()
        //passing the arguments
        fragment.arguments = args
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        if(arguments != null){
            //probably where we will fill up the view with text of some sort
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return the created fragment
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_forecast_main, container, false)
        var view = binder.root
        viewModel = UserMainActivityViewModel()
        binder.viewModel = viewModel

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO change the variables in the view to reflect the information that was recieved
    }

    override fun onResume() {
        preferences = this.activity!!.getSharedPreferences("forecast",Context.MODE_PRIVATE)
        super.onResume()
        viewModel.currentCast(preferences.getString("Zip","10004")!!)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is onFragmentInteractionListener) {
            mListener = context as onFragmentInteractionListener
        } else {
            throw RuntimeException(context.toString() + "must implement method")
        }
    }

    interface onFragmentInteractionListener {
        fun recieveData(message: String)
    }


}
