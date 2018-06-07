package br.com.barcadero.geniususer.activities

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.barcadero.geniususer.util.ShowCaseViewUtil
import org.jetbrains.annotations.Nullable

/**
 * Created by Rafael Rocha on 05/06/2018.
 */
open class BaseActivity: AppCompatActivity() {

    protected fun showCaseOnView(viewId:Int,title:String,text:String){
        ShowCaseViewUtil.singleShot(viewId, this,text,title,resources)
    }

    protected fun includeFragment(@IdRes containerViewId:Int, fragment: Fragment, @Nullable bundle: Bundle? = null, title: String = "Title"){
        val fm = supportFragmentManager.beginTransaction()
        if(bundle != null){
            fragment.arguments = bundle
        }
        fm.add(containerViewId, fragment,title)
        fm.commit()
    }
}