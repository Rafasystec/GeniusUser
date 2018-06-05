package br.com.barcadero.geniususer.model.responses

import br.com.transferr.model.enums.EnumFailSystem

/**
 * Created by idoctor on 05/06/2018.
 */
class MapErroRetornoRest {

    var type: EnumFailSystem? = null
    var message: String? = null
    var rootCause: String? = null
    var rootMessage: String? = null
    var fields: Array<String>? = null
}