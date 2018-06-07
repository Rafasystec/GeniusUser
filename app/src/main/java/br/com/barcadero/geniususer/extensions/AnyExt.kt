package br.com.barcadero.geniususer.extensions

import android.content.Context
import android.widget.Toast
import br.com.barcadero.geniususer.application.SystemApplication

/**
 * Created by Rafael Rocha on 07/06/2018 at 14:13.
 */
fun toast(message: CharSequence) {
    Toast.makeText(SystemApplication.getInstance().applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun toast(message: CharSequence, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}