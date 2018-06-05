package br.com.barcadero.geniususer.webservices

import br.com.barcadero.geniususer.application.SystemApplication
import br.com.transferr.util.NetworkUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Rafael Rocha on 05/06/2018.
 */
open class BaseWebService {
    protected var urlBase = SystemApplication.getInstance().URL_BASE
    protected var httpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()

    protected val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    protected fun isConnected():Boolean{
        return NetworkUtil.isNetworkAvailable(SystemApplication.getInstance().applicationContext)
    }
}