package br.com.barcadero.geniususer.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.android.adapters.ClientHistoricAdapter
import br.com.barcadero.geniususer.extensions.defaultRecycleView
import br.com.barcadero.geniususer.model.enums.EnumProfessionalArea
import br.com.barcadero.geniususer.model.responses.ClientHistoricResponse


/**
 * A simple [Fragment] subclass.
 */
class ClientHistoryFragment : Fragment() {


    var recycleView:RecyclerView?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_history, container, false)
    }

    override fun onResume() {
        super.onResume()
        loadClientHistoric()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView = defaultRecycleView(view,R.id.rcClientHistoric)
    }

    private fun loadClientHistoric(){
        var professional = ClientHistoricResponse()
        with(professional){
            professionalName = "Jose Arruda"
            urlPhotoPro=""
            currentRate=3
            isFavorite=false
            value="430,50"
            enumArea = EnumProfessionalArea.BRICKWORK
        }

        var professional2 = ClientHistoricResponse()
        with(professional2){
            professionalName = "Marcus de Oliveira"
            urlPhotoPro=""
            currentRate=3
            isFavorite=false
            value="80,50"
            enumArea = EnumProfessionalArea.MECHANICS
        }

        var professional3 = ClientHistoricResponse()
        with(professional3){
            professionalName = "Marcus de Senna Paiva"
            urlPhotoPro=""
            currentRate=3
            isFavorite=false
            value="80,50"
            enumArea = EnumProfessionalArea.MECHANICS
        }

        var professional4 = ClientHistoricResponse()
        with(professional4){
            professionalName = "Armando Ney Coelho"
            urlPhotoPro=""
            currentRate=3
            isFavorite=true
            value="180,00"
            enumArea = EnumProfessionalArea.AIR_CONDITIONING
        }

        var professional5 = ClientHistoricResponse()
        with(professional5){
            professionalName = "Jose Arruda bastos"
            urlPhotoPro=""
            currentRate=4
            isFavorite=true
            value="115,00"
            enumArea = EnumProfessionalArea.DIARIST
        }

        var professional6 = ClientHistoricResponse()
        with(professional6){
            professionalName = "Any Almeida"
            urlPhotoPro=""
            currentRate=5
            isFavorite=true
            value="100,00"
            enumArea = EnumProfessionalArea.PAINTING
        }

        recycleView?.adapter = ClientHistoricAdapter(listOf(professional,professional2,professional3,professional4,professional5,professional6),{professional:ClientHistoricResponse -> onClick(professional)})
    }

    fun onClick(professional:ClientHistoricResponse){
        Log.d("NOTHING","OnClick Client Historic")
    }

}// Required empty public constructor
