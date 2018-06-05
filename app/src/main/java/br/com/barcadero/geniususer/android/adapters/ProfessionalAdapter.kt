package br.com.barcadero.geniususer.android.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.model.Professional
import br.com.barcadero.geniususer.model.responses.ProfessionalResponse
import com.squareup.picasso.Picasso

/**
 * Created by idoctor on 05/06/2018.
 */
class ProfessionalAdapter(
        val professionals: List<ProfessionalResponse>,
        val onClick: (ProfessionalResponse) -> Unit) : RecyclerView.Adapter<ProfessionalAdapter.ProfessionalViewHolder>(){

    override fun onBindViewHolder(holder: ProfessionalViewHolder, position: Int) {
        val context = holder.itemView.context
        val professional = professionals[position]
        holder.tvName.text      = professional.name
        holder.tvPrice.text     = professional.value
        holder.tvDistance.text  = professional.distance
        //Start progressBar
        //holder.progress.visibility = View.Visible
        Picasso.with(context).load(professional.urlPhoto).fit().into(holder.img,
                object : com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        //Stop progress bar
                    }

                    override fun onError() {
                        //Stop progress bar
                    }

                })
        holder.itemView.setOnClickListener { onClick(professional) }

    }


    /**
     * Infla o Layout do Adapter e retorna a Holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionalViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_professionals,parent,false)
        return ProfessionalViewHolder(view)
    }

    override fun getItemCount() = this.professionals.size


    class ProfessionalViewHolder(view:View):RecyclerView.ViewHolder(view){
        var tvName    : TextView
        var tvPrice   : TextView
        var tvDistance: TextView
        var img: ImageView
        //var progress:ProgressBar
        var cardView:CardView
        init {
            tvName      = view.findViewById(R.id.tvNameProfessional)
            img         = view.findViewById(R.id.ivProfile)
            tvPrice     = view.findViewById(R.id.tvPrice)
            tvDistance  = view.findViewById(R.id.tvDistance)
            cardView    = view.findViewById(R.id.cvProfessional)
        }
    }
}