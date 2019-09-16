package com.example.week5weekendhwmvvm.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.example.week5weekendhwmvvm.R
import com.example.week5weekendhwmvvm.databinding.ActivityMainBinding
import com.example.week5weekendhwmvvm.view.fragments.MainForecastFragment
import com.example.week5weekendhwmvvm.view.fragments.SecondaryScrollFragment
import com.example.week5weekendhwmvvm.viewmodel.UserMainActivityViewModel
import android.content.DialogInterface
import android.widget.EditText
import android.view.LayoutInflater

import androidx.appcompat.app.AlertDialog
import android.content.SharedPreferences
import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity(), MainForecastFragment.onFragmentInteractionListener, SecondaryScrollFragment.onFragmentInteractionListener {

    lateinit var zipCode : String

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var userMainActivityViewModel: UserMainActivityViewModel

    //Fragment boys
    lateinit var mainForecastFragment: MainForecastFragment
    lateinit var secondaryScrollFragment: SecondaryScrollFragment
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userMainActivityViewModel = UserMainActivityViewModel()
        //
        activityMainBinding.mainActivityViewModel

        Log.d("NETWORK","PLZ WORK")
        //calling the data from the web
        userMainActivityViewModel.ForecastCall("30296")

        fragmentManager = supportFragmentManager
        //Fragments
        mainForecastFragment = MainForecastFragment()
        secondaryScrollFragment = SecondaryScrollFragment()

        mainForecastFragment.newInstance()
        secondaryScrollFragment.newInstance()

        // Actual replacing of the fragment
        fragmentManager.beginTransaction().add(R.id.FramelayoutPrimary, mainForecastFragment)
           .addToBackStack("primeDisplay").commit()
        fragmentManager.beginTransaction().add(R.id.FramelayoutSecondary, secondaryScrollFragment)
            .addToBackStack("secondaryDisplay").commit()

        //initializing everything
        //userMainActivityViewModel.currentCast("30274")

        //chains into everything else
        getZip()
    }

    //the data method for sending info around
    override fun recieveData(message: String) {
    }

    fun getZip(){
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.zip_entry_dialog, null)

        val etZip = view.findViewById(R.id.etZipEntry) as EditText
        val dialog = AlertDialog.Builder(this)
            .setTitle("Enter the Zip Code")
            .setView(view)
            .setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, whichButton ->
                    zipCode = etZip.text.toString()
                    val editor = getSharedPreferences("forecast", Context.MODE_PRIVATE).edit()
                    editor.putString("Zip", etZip.getText().toString())
                    editor.apply()

                    //
                    // userMainActivityViewModel.currentCast(etZip.getText().toString())

                    val keys = getSharedPreferences("forecast", Context.MODE_PRIVATE)

                    //attempt to refresh fragment
                    fragmentManager.beginTransaction().detach(mainForecastFragment).attach(mainForecastFragment).commit()
                })
            .setNegativeButton("Cancel", null).create()
        dialog.show()
    }

}
