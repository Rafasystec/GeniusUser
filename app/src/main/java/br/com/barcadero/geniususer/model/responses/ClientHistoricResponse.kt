package br.com.barcadero.geniususer.model.responses

import br.com.barcadero.geniususer.model.enums.EnumProfessionalArea

/**
 * Created by idoctor on 12/06/2018.
 */
class ClientHistoricResponse {
    var professionalName = ""
    var value = ""
    var currentRate = 0
    var isFavorite = false
    var urlPhotoPro:String? = null
    var enumArea = EnumProfessionalArea.UNKNOW
}