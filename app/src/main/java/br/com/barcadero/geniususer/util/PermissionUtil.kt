package br.com.transferr.util

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat


/**
 * Created by idoctor on 09/02/2018.
 */

object PermissionUtil{
    fun validate(activity: Activity, code: Int,vararg permissions: String):Boolean{
        val list = ArrayList<String>()
        for (permission in permissions){
            val allowed = ContextCompat.checkSelfPermission(activity,permission) == PackageManager.PERMISSION_GRANTED
            if(!allowed){
                list.add(permission)
            }
        }
        if(list.isEmpty()){
            return true
        }
        val newPermissions = arrayOfNulls<String>(list.size)
        ActivityCompat.requestPermissions(activity,newPermissions,1)
        return false
    }

    fun hasAllPermissions(activity: Activity, vararg permissions: String): Boolean {

        var hasAllPermissions = true

        for (permission in permissions) {
            if (!hasPermission(activity, permission)) {
                hasAllPermissions = false
            }
        }

        return hasAllPermissions
    }

    fun useRunTimePermissions(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
    }

    fun hasPermission(activity: Activity, permission: String): Boolean {
        return if (useRunTimePermissions()) {
            activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        } else true
    }

    fun requestPermissions(activity: Activity, permission: Array<String>, requestCode: Int) {
        if (useRunTimePermissions()) {
            activity.requestPermissions(permission, requestCode)
        }
    }

    fun requestPermissions(fragment: Fragment, permission: Array<String>, requestCode: Int) {
        if (useRunTimePermissions()) {
            fragment.requestPermissions(permission, requestCode)
        }
    }
}