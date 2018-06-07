package br.com.barcadero.geniususer.android.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.model.responses.CommentsProfessionalResponse
import com.squareup.picasso.Picasso

/**
 * Created by Rafael Rocha on 05/06/2018.
 */
class ProfessionalCommentsAdapter(
        val professionalComments: List<CommentsProfessionalResponse>,
        val onClick: (CommentsProfessionalResponse) -> Unit) : RecyclerView.Adapter<ProfessionalCommentsAdapter.ProfessionalViewHolder>(){

    override fun onBindViewHolder(holder: ProfessionalViewHolder, position: Int) {
        val context = holder.itemView.context
        val comment = professionalComments[position]
        holder.tvClientName.text      = comment.clientName
        holder.tvCommentText.text     = comment.comment
        holder.tvCommentDate.text     = comment.commentDate

        //Start progressBar
        //holder.progress.visibility = View.Visible
        Picasso.with(context).load(comment.photoClient).fit().into(holder.img,
                object : com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        //Stop progress bar
                    }

                    override fun onError() {
                        //Stop progress bar
                    }

                })
        holder.itemView.setOnClickListener { onClick(comment) }

    }


    /**
     * Infla o Layout do Adapter e retorna a Holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionalViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_professionals_comments,parent,false)
        return ProfessionalViewHolder(view)
    }

    override fun getItemCount() = this.professionalComments.size


    class ProfessionalViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tvClientName    : TextView
        var tvCommentText   : TextView
        var tvCommentDate   : TextView
        var img: ImageView
        //var progress:ProgressBar
        var cardView: CardView
        init {
            tvClientName = view.findViewById(R.id.tvClientName)
            img          = view.findViewById(R.id.ivProfileClientComment)
            tvCommentText= view.findViewById(R.id.tvCommentText)
            tvCommentDate= view.findViewById(R.id.tvCommentDate)
            cardView     = view.findViewById(R.id.cvProfComment)
        }
    }
}