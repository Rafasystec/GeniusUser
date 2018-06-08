package br.com.barcadero.geniususer.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.activities.ClientAgendaDetailsActivity
import br.com.barcadero.geniususer.android.adapters.ClientAgendaAdapter
import br.com.barcadero.geniususer.extensions.defaultRecycleView
import br.com.barcadero.geniususer.model.responses.ClientAgendaResponse


/**
 * A simple [Fragment] subclass.
 * @author Rafael Rocha on 08/06/2018 as 11:03
 */
class ClientAgendaFragment : BaseFragment() {


    var recycleView:RecyclerView?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_client_agenda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView = defaultRecycleView(view,R.id.rcClientAgenda)
    }

    override fun onResume() {
        super.onResume()
        loadAdapter()
    }

    private fun loadAdapter() {
        var agendaClient = ClientAgendaResponse()
        agendaClient.professionalPhotoUrl="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSdL1s1tMcbRkQm2VhwJUVrKmFTAqm4FprzAtAMONpZVTkFjFPlA"
        agendaClient.professionalName="Carlos Aires Brito"
        agendaClient.status="Esperando confirmação"
        agendaClient.agendaDate="05/06/2018  08:00 as 11:00"
        var agendaClient1 = ClientAgendaResponse()
        agendaClient1.professionalPhotoUrl="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDCtOFJuZ9gaLW9WLXGSYzoJM0a8H8ejYEqoIs9Rg7kKAPT26P"
        agendaClient1.professionalName="Durval Lelys"
        agendaClient1.status="Confirmado"
        agendaClient1.agendaDate="01/06/2018  14:00 as 16:00"
        agendaClient1.confirmed=true
        recycleView?.adapter = ClientAgendaAdapter(listOf(agendaClient,agendaClient1),onClick = {startAgendaClientDetails(agendaClient)})
    }

    fun startAgendaClientDetails(agendaResponse: ClientAgendaResponse){
        var intent = Intent(context,ClientAgendaDetailsActivity::class.java)
        startActivity(intent)
    }
}// Required empty public constructor
