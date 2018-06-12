package br.com.barcadero.geniususer.android.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.model.responses.ClientHistoricResponse
import com.squareup.picasso.Picasso

/**
 * Created by idoctor on 05/06/2018.
 */
class ClientFavoriteAdapter(
        val professionals: List<ClientHistoricResponse>,
        val onClick: (ClientHistoricResponse) -> Unit) : RecyclerView.Adapter<ClientFavoriteAdapter.ProfessionalViewHolder>(){

    override fun onBindViewHolder(holder: ProfessionalViewHolder, position: Int) {
        val context = holder.itemView.context
        val professional = professionals[position]
        holder.tvName.text      = professional.professionalName
        holder.tvPrice.text     = professional.value
        when(professional.currentRate){
            1 -> setColor(holder.ivStarRate1,context)
            2 -> {setColor(holder.ivStarRate1,context)
                  setColor(holder.ivStarRate2,context)}
            3 ->{setColor(holder.ivStarRate1,context)
                setColor(holder.ivStarRate2,context)
                setColor(holder.ivStarRate3,context)}
            4 ->{setColor(holder.ivStarRate1,context)
                setColor(holder.ivStarRate2,context)
                setColor(holder.ivStarRate3,context)
                setColor(holder.ivStarRate4,context)}
            5 ->{setColor(holder.ivStarRate1,context)
                setColor(holder.ivStarRate2,context)
                setColor(holder.ivStarRate3,context)
                setColor(holder.ivStarRate4,context)
                setColor(holder.ivStarRate5,context)}
        }

        if(professional.isFavorite){
            holder.ivFavoritePro.visibility = View.VISIBLE
        }else{
            holder.ivFavoritePro.visibility = View.GONE
        }
        holder.tvCliHistoricAreaPro.text = professional.enumArea.description
        if(professional.urlPhotoPro?.isEmpty()!!){
            professional.urlPhotoPro = null
        }
        //Start progressBar
        //holder.progress.visibility = View.Visible
        Picasso.with(context).load(professional.urlPhotoPro).placeholder(R.drawable.no_photo_64).fit().into(holder.img,
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_client_historic,parent,false)
        return ProfessionalViewHolder(view)
    }

    override fun getItemCount() = this.professionals.size

    private fun setColor(imageView: ImageView, context: Context){
        imageView.setColorFilter(context.resources.getColor(R.color.colorAccentClient))
    }
    class ProfessionalViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tvName    : TextView
        var tvPrice   : TextView
        var img: ImageView
        var ivStarRate1: ImageView
        var ivStarRate2: ImageView
        var ivStarRate3: ImageView
        var ivStarRate4: ImageView
        var ivStarRate5: ImageView
        var ivFavoritePro: ImageView
        var tvCliHistoricAreaPro: TextView
        //var progress:ProgressBar
        var cardView: CardView
        init {
            tvName      = view.findViewById(R.id.tvNameProfessional)
            img         = view.findViewById(R.id.ivProfile)
            tvPrice     = view.findViewById(R.id.tvPrice)
            cardView    = view.findViewById(R.id.cvProfessional)
            ivStarRate1    = view.findViewById(R.id.ivStarRate1)
            ivStarRate2    = view.findViewById(R.id.ivStarRate2)
            ivStarRate3    = view.findViewById(R.id.ivStarRate3)
            ivStarRate4    = view.findViewById(R.id.ivStarRate4)
            ivStarRate5    = view.findViewById(R.id.ivStarRate5)
            ivFavoritePro    = view.findViewById(R.id.ivFavoritePro)
            tvCliHistoricAreaPro    = view.findViewById(R.id.tvCliHistoricAreaPro)
        }
    }
}