package br.com.barcadero.geniususer.application

import android.app.Application

/**
 * Created by Rafael Rocha on 05/06/2018.
 */
class SystemApplication : Application(){

    private val TAG = "APPLICATION"
    //private val base = "http://192.168.15.9:8080/"    //For Linux
    private val base = "http://192.168.0.111:8080/" //For windows
    //private val base = "http://petmooby.com.br/transferr-rest/"
    val URL_BASE        = base +"genius-rs/rest/"
    //val URL_BASE        = base +"rest/" //For hospedagem
    val URL_BASE_IMG    = base +"files/"
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: SystemApplication? = null
        fun getInstance(): SystemApplication{
            if (appInstance == null){
                throw IllegalStateException("Configure the Application class on Manifest xml.")
            }
            return appInstance!!
        }
    }
}