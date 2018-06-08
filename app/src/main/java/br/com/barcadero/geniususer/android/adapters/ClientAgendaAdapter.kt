package br.com.barcadero.geniususer.android.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.model.enums.EnumTypeUser
import br.com.barcadero.geniususer.model.responses.ClientAgendaResponse
import br.com.transferr.util.Prefes
import com.squareup.picasso.Picasso

/**
 * Created by Rafael Rocha on 05/06/2018.
 */
class ClientAgendaAdapter(
        val agendas: List<ClientAgendaResponse>,
        val onClick: (ClientAgendaResponse) -> Unit) : RecyclerView.Adapter<ClientAgendaAdapter.ProfessionalViewHolder>(){

    val typeUser = Prefes.prefsTypeUser
    override fun onBindViewHolder(holder: ProfessionalViewHolder, position: Int) {
        val context = holder.itemView.context
        val agenda  = agendas[position]
        var name: String
        var urlPhoto: String
        if(typeUser == EnumTypeUser.PROFESSIONAL) {
            name = agenda.clientName
            urlPhoto = agenda.clientPhotoUrl
        }else{
            name = agenda.professionalName
            urlPhoto = agenda.professionalPhotoUrl
        }
        holder.tvAgendaClientProfName.text = name
        holder.tvClientAgendaStatus.text = agenda.status
        //Start progressBar
        //holder.progress.visibility = View.Visible
        if (agenda.confirmed) {
            holder.ivAgendaClientAdapterOk.visibility = View.VISIBLE
        } else {
            holder.ivAgendaClientAdapterOk.visibility = View.GONE
        }
        Picasso.with(context).load(urlPhoto).fit().into(holder.ivAgendaClient,
                object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        //Stop progress bar
                    }

                    override fun onError() {
                        //Stop progress bar
                    }
                })
        holder.itemView.setOnClickListener { onClick(agenda) }
    }

    /**
     * Infla o Layout do Adapter e retorna a Holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionalViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_client_agenda,parent,false)
        return ProfessionalViewHolder(view)
    }

    override fun getItemCount() = this.agendas.size


    class ProfessionalViewHolder(view: View): RecyclerView.ViewHolder(view){
        var ivAgendaClient: ImageView
        var ivAgendaClientAdapterOk: ImageView
        var tvAgendaClientProfName : TextView
        var tvClientAgendaStatus : TextView
        init {
            ivAgendaClient          = view.findViewById(R.id.ivAgendaClient)
            tvAgendaClientProfName  = view.findViewById(R.id.tvAgendaClientProfName)
            tvClientAgendaStatus    = view.findViewById(R.id.tvClientAgendaStatus)
            ivAgendaClientAdapterOk= view.findViewById(R.id.ivAgendaClientAdapterOk)
        }
    }
}