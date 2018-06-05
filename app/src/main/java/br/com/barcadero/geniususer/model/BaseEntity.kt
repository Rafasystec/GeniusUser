package br.com.barcadero.geniususer.model

import java.io.Serializable
import java.util.*

/**
 * Created by Rafael Rocha on 05/06/2018.
 */
open class BaseEntity :Serializable{
    var id:Long?=0
    var dtRegister:Date?=null
}