package br.com.barcadero.geniususer.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.activities.ProfessionalAgendaActivity
import br.com.barcadero.geniususer.activities.ProfessionalAgendaDetailsActivity
import br.com.barcadero.geniususer.android.adapters.ClientAgendaAdapter
import br.com.barcadero.geniususer.extensions.defaultRecycleView
import br.com.barcadero.geniususer.model.responses.ClientAgendaResponse


/**
 * @author Rafael Rocha on 08/06/2018
 */
class ProfessionalAgendaFragment : Fragment() {

    var recycleView: RecyclerView?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_professional_agenda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView = defaultRecycleView(view,R.id.rcProfessionalAgenda)
    }

    override fun onResume() {
        super.onResume()
        loadAdapter()
    }

    private fun loadAdapter() {
        var agendaClient = ClientAgendaResponse()
        with(agendaClient){
            professionalPhotoUrl="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSdL1s1tMcbRkQm2VhwJUVrKmFTAqm4FprzAtAMONpZVTkFjFPlA"
            professionalName="Carlos Aires Brito"
            status="Esperando confirmação"
            agendaDate="05/06/2018  08:00 as 11:00"
            clientName="Juliana Moreira da Rocha"
            clientPhotoUrl="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnkZsz6_S7yr7AwceGvx62k2Km2bhWHEx8CaEgLb9_TwdKqobDgA"
        }
        recycleView?.adapter = ClientAgendaAdapter(listOf(agendaClient),onClick = {startAgendaProfessionalDetails(agendaClient)})
    }

    fun startAgendaProfessionalDetails(agendaResponse: ClientAgendaResponse){
        var intent = Intent(context, ProfessionalAgendaDetailsActivity::class.java)
        startActivity(intent)
    }
}
