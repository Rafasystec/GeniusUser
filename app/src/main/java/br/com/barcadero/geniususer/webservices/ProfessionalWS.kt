package br.com.barcadero.geniususer.webservices

import br.com.barcadero.geniususer.interfaces.OnResponseInterface
import br.com.barcadero.geniususer.model.Professional
import br.com.barcadero.geniususer.model.objects.Filter
import br.com.barcadero.geniususer.model.responses.ProfessionalResponse
import br.com.barcadero.geniususer.model.responses.ResponseOK
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Rafael Rocha on 05/06/2018.
 */
const val ROOT_URL = "pro"
object ProfessionalWS : BaseWebService() {

    private var service : IProfessionalWS = retrofit.create(IProfessionalWS::class.java)
    fun save(professional: Professional,responseInterface: OnResponseInterface<Professional>){
        service.save(professional).enqueue(CallBackWS(responseInterface))
    }

    fun delete(id: Long,responseInterface: OnResponseInterface<ResponseOK>){
        service.delete(id).enqueue(CallBackWS(responseInterface))
    }

    fun getByFilter(filter:Filter,responseInterface: OnResponseInterface<List<ProfessionalResponse>>){
        service.getByFilter(filter).enqueue(CallBackWS(responseInterface))
    }

}


interface IProfessionalWS{
    @POST(ROOT_URL)
    fun save(@Body professional: Professional): Call<Professional>
    @GET(ROOT_URL+"/{id}")
    fun get(@Path("id") id:Long): Call<Professional>
    @GET(ROOT_URL+"/filter")
    fun getByFilter(@Body filter:Filter): Call<List<ProfessionalResponse>>
    @DELETE(ROOT_URL+"/{id}")
    fun delete(@Path("id") id:Long): Call<ResponseOK>
}