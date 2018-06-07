package br.com.barcadero.geniususer.extensions

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.barcadero.geniususer.android.dialogs.MessageDialog

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