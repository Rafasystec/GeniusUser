package br.com.barcadero.geniususer.fragments

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import br.com.transferr.extensions.showError

/**
 * Created by Rafael Rocha on 05/06/2018.
 */
open class BaseFragment : Fragment(){

    fun toast(message:String ,context:FragmentActivity){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}