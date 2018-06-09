package br.com.barcadero.geniususer.android.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.model.responses.LocationStoreResponse
import com.squareup.picasso.Picasso

/**
 * Created by idoctor on 05/06/2018.
 */
class LocationStoreAdapter(
        val stores: List<LocationStoreResponse>,
        val onClick: (LocationStoreResponse) -> Unit) : RecyclerView.Adapter<LocationStoreAdapter.ProfessionalViewHolder>(){

    override fun onBindViewHolder(holder: ProfessionalViewHolder, position: Int) {
        val context = holder.itemView.context
        val store   = stores[position]
        with(holder){
            tvDistanceLocalStore.text = store.distance
            tvLocalStoreAddress.text = store.address
            tvLocalStoreRate.text = store.rate
            tvLocationStoreName.text = store.storeName
        }
        //Start progressBar
        //holder.progress.visibility = View.Visible
        Picasso.with(context).load(store.urlPhoto).fit().into(holder.ivProfileLocationStore,
                object : com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        //Stop progress bar
                    }

                    override fun onError() {
                        //Stop progress bar
                    }

                })
        holder.itemView.setOnClickListener { onClick(store) }

    }


    /**
     * Infla o Layout do Adapter e retorna a Holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionalViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_location_store,parent,false)
        return ProfessionalViewHolder(view)
    }

    override fun getItemCount() = this.stores.size


    class ProfessionalViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tvLocationStoreName     : TextView
        var tvLocalStoreAddress     : TextView
        var tvDistanceLocalStore    : TextView
        var ivProfileLocationStore  : ImageView
        var tvLocalStoreRate        : TextView
        //var progress:ProgressBar
        var cvPLocalStore: CardView
        init {
            tvLocationStoreName     = view.findViewById(R.id.tvLocationStoreName)
            tvLocalStoreAddress     = view.findViewById(R.id.tvLocalStoreAddress)
            tvDistanceLocalStore    = view.findViewById(R.id.tvDistanceLocalStore)
            ivProfileLocationStore  = view.findViewById(R.id.ivProfileLocationStore)
            tvLocalStoreRate        = view.findViewById(R.id.tvLocalStoreRate)
            cvPLocalStore           = view.findViewById(R.id.cvPLocalStore)
        }
    }
}