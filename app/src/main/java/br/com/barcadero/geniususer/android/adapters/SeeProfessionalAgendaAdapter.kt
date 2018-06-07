package br.com.barcadero.geniususer.android.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.model.ProfessionalAgenda
import br.com.barcadero.geniususer.model.responses.ProfessionalResponse
import com.squareup.picasso.Picasso

/**
 * Created by idoctor on 05/06/2018.
 */
class SeeProfessionalAgendaAdapter(
        val agendas: List<ProfessionalAgenda>,
        val onClick: (ProfessionalAgenda) -> Unit) : RecyclerView.Adapter<SeeProfessionalAgendaAdapter.ProfessionalViewHolder>(){

    override fun onBindViewHolder(holder: ProfessionalViewHolder, position: Int) {
        val context = holder.itemView.context
        val agenda  = agendas[position]
        holder.tvAgendaHour.text = agenda.hour
        if(agenda.isBusy) {
            holder.tvVancat.text = "OCUPADO"
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bootstrapAlertDanger))
        }else{
            holder.tvVancat.text = "LIVRE"
            //holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bootstrapAlertInfor))
        }
        holder.itemView.setOnClickListener { onClick(agenda) }
    }

    /**
     * Infla o Layout do Adapter e retorna a Holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionalViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_see_professional_agenda,parent,false)
        return ProfessionalViewHolder(view)
    }

    override fun getItemCount() = this.agendas.size


    class ProfessionalViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tvAgendaHour    : TextView
        var tvVancat   : TextView
        var cardView: CardView
        init {
            tvAgendaHour= view.findViewById(R.id.tvAgendaHour)
            tvVancat    = view.findViewById(R.id.tvVancat)
            cardView    = view.findViewById(R.id.cvSeeAgenda)
        }
    }
}