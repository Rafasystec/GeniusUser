package br.com.barcadero.geniususer.interfaces

/**
 * Created by Rafael Rocha on 05/06/2018.
 */
interface OnResponseInterface<T> {

    fun onSuccess(body:T?)
    fun onError(message:String)
    fun onFailure( t: Throwable?)
}