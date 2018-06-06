package br.com.barcadero.geniususer.activities

import android.support.v7.app.AppCompatActivity
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.util.ShowCaseViewUtil

/**
 * Created by Rafael Rocha on 05/06/2018.
 */
open class BaseActivity: AppCompatActivity() {

    protected fun showCaseOnView(viewId:Int,title:String,text:String){
        ShowCaseViewUtil.singleShot(viewId, this,text,title,resources)
    }
}