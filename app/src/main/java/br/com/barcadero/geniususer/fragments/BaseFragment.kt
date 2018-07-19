package br.com.barcadero.geniususer.fragments

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import br.com.barcadero.geniususer.activities.MainActivity
import br.com.barcadero.geniususer.util.AlertUtil
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton

/**
 * Created by Rafael Rocha on 05/06/2018.
 */
open class BaseFragment : Fragment(){

    protected var PLACE_AUTOCOMPLETE_REQUEST_CODE = 1
    fun toast(message:String ,context:FragmentActivity){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun setTitleBarForClient(title:String){
        (activity as MainActivity)
                .setActionBarTitle(title)
    }

    protected fun setTitleBarForClient(@StringRes id:Int){
        (activity as MainActivity)
                .setActionBarTitle(activity?.resources?.getString(id)!!)
    }

    protected fun alertErro(message: String){
        activity!!.alert ( message,AlertUtil.DEFAULT_ERROR_TITLE){
            okButton { it.dismiss() }
        }.show()
    }

    protected fun alertWarning(message: String){
        activity!!.alert ( message,AlertUtil.DEFAULT_VALIDATE_TITLE){
            okButton { it.dismiss() }
        }.show()
    }

    protected fun alertSuccess(message: String){
        activity!!.alert ( message,AlertUtil.DEFAULT_VALIDATE_TITLE){
            okButton { it.dismiss() }
        }.show()
    }
}