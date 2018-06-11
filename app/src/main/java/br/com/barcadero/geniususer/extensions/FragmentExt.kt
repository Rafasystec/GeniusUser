package br.com.barcadero.geniususer.extensions

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.R.id.spSexoBasic
import br.com.barcadero.geniususer.android.dialogs.MessageDialog
import br.com.barcadero.geniususer.model.enums.EnumSexo

/**
 * Created by idoctor on 07/06/2018.
 */


inline fun Fragment.defaultRecycleView(view: View, resId:Int): RecyclerView{
    //recycleView = view.findViewById(R.id.rcFindProf)
    var recycleView = view.findViewById<RecyclerView>(resId)
    recycleView?.layoutManager = LinearLayoutManager(activity)
    recycleView?.itemAnimator = DefaultItemAnimator()
    recycleView?.setHasFixedSize(true)
    return recycleView
}

inline fun showDialog(context: Context, text: String, title: String){
    var dialog      = MessageDialog(context)
    dialog.text     = text
    dialog.title    = title
    dialog.show()
}

inline fun initSpinnerSexo(activity:FragmentActivity):Spinner{
    var spSexoBasic = activity.findViewById<Spinner>(R.id.spSexoBasic)
    spSexoBasic.adapter = ArrayAdapter(activity, R.layout.support_simple_spinner_dropdown_item, listOf(activity.resources.getString(R.string.male) ,activity.resources.getString(R.string.female)))
    /*
    spSexoBasic.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
           val gender = if(position == 0){
                EnumSexo.MALE
            }else{
                EnumSexo.FEMALE
            }
            onSelectedItemResult(gender)
        }
    }
    */
    return spSexoBasic
}

inline fun getGenderAdapter(activity: FragmentActivity): ArrayAdapter<String>{
    return ArrayAdapter(activity, R.layout.support_simple_spinner_dropdown_item, listOf(activity.resources.getString(R.string.male) ,activity.resources.getString(R.string.female)))
}

