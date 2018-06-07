package br.com.barcadero.geniususer.util

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText

/**
 * Created by idoctor on 06/06/2018.
 */
class TextWatcherUtil(maxLength:Int , editTextNext: EditText) : TextWatcher {

    val max = maxLength
    val edt = editTextNext
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if(s?.length == max){
            edt.requestFocus(View.FOCUS_DOWN)
        }
    }

    @SuppressLint("RestrictedApi")
    fun removeShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView

                item.setShiftingMode(false)
                // set once again checked value, so view will be updated

                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            Log.e("BottomNav", "Unable to get shift mode field", e)
        } catch (e: IllegalAccessException) {
            Log.e("BottomNav", "Unable to change value of shift mode", e)
        }

    }
}