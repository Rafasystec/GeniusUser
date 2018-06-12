package br.com.barcadero.geniususer.model.enums

import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.application.SystemApplication

/**
 * Created by Rafael Rocha on 06/06/2018.
 */
enum class EnumProfessionalArea(val description:String) {

    WOODWORK(SystemApplication.getInstance().applicationContext.resources.getString(R.string.area_woodwork)),
    AIR_CONDITIONING(SystemApplication.getInstance().applicationContext.resources.getString(R.string.area_air_conditioning)),
    ELETRICAL(SystemApplication.getInstance().applicationContext.resources.getString(R.string.area_eletrical)),
    DIARIST(SystemApplication.getInstance().applicationContext.resources.getString(R.string.area_diarist)),
    BRICKWORK(SystemApplication.getInstance().applicationContext.resources.getString(R.string.area_brickwork)),
    PAINTING(SystemApplication.getInstance().applicationContext.resources.getString(R.string.area_painting)),
    MECHANICS(SystemApplication.getInstance().applicationContext.resources.getString(R.string.area_mechanics)),
    UNKNOW(SystemApplication.getInstance().applicationContext.resources.getString(R.string.area_unknow))

}