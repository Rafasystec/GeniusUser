package br.com.barcadero.geniususer.model.responses

import br.com.barcadero.geniususer.model.enums.EnumProfessionalArea

/**
 * Created by idoctor on 05/06/2018.
 */
class ProfessionalResponse {
    var id = 0L
    var name = ""
    var value = ""
    var distance = ""
    var urlPhoto = ""
    var longitude:Double?=null
    var latitude:Double?=null
    var professionalArea:EnumProfessionalArea?=null
    var rate = 0
    var isFavorite = false
}