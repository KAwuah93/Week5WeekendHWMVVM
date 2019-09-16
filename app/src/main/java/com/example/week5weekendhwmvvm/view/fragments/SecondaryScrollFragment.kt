package com.example.week5weekendhwmvvm.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week5weekendhwmvvm.R

class SecondaryScrollFragment : Fragment {
    //Todo for the click and scroll functionality boyo
    lateinit var mListener : onFragmentInteractionListener

    constructor()

    //Getting an instance of the fragment
    fun newInstance() : SecondaryScrollFragment{
        val fragment = SecondaryScrollFragment()
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
        return LayoutInflater.from(context).inflate(R.layout.fragment_forecast_secondary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO get that info the the fragments
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