package br.com.barcadero.geniususer.model

import java.util.*

/**
 * Created by Rafael Rocha on 07/06/2018.
 */
class ProfessionalAgenda: BaseEntity() {
    var hour = ""
    var dateAgenda:Date?=null
    var isBusy=false
}