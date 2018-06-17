package br.com.barcadero.geniususer.webservices

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Rafael Rocha on 16/06/2018.
 */

const val ROOT_URL_AGENDA = "agenda"
class AgendaWS : BaseWebService(){
    private var service : IAgendaWS = retrofit.create(IAgendaWS::class.java)
    fun doGetListClientAgenda(idClient: Long){
        service.doGetListClientAgenda(idClient)
    }
}

interface IAgendaWS{

    @GET(ROOT_URL_AGENDA+"/{idClient}")
    fun doGetListClientAgenda(@Path("idClient") idClient:Long)
}