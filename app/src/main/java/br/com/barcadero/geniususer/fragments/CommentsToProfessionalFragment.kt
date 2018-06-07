package br.com.barcadero.geniususer.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.android.adapters.ProfessionalCommentsAdapter
import br.com.barcadero.geniususer.extensions.defaultRecycleView
import br.com.barcadero.geniususer.model.responses.CommentsProfessionalResponse


/**
 * A simple [Fragment] subclass.
 */
class CommentsToProfessionalFragment : BaseFragment() {

    var recycleView: RecyclerView?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments_to_professional, container, false)
    }

    override fun onResume() {
        super.onResume()
        loadProfessionalComments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView = defaultRecycleView(view,R.id.rcProfessionalComments)

    }

    private fun loadProfessionalComments() {
        var comment = CommentsProfessionalResponse()
        comment.photoClient = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVLJS2iYiIg0sKLBPWpnWRlhZd6xdNe2B8vGts1WDLQ5nWeBMv"
        comment.clientName = "Raul Gomes"
        comment.comment = "Não gostei, chegou atrasado e deu muita diferença entre o orçamento."
        comment.idClient = 1L
        comment.commentDate = "07/06/2018 as 09:45"
        comment.idProfessional = 3L
        comment.like=false

        var comment1 = CommentsProfessionalResponse()
        comment1.photoClient = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqw0W807U9POJusqotyCoL7HwvAVJjEla49kj4X3ouJXRWex_rWQ"
        comment1.clientName = "Giovana Araújo"
        comment1.comment =
                "Este trecho é parte de conteúdo que pode ser compartilhado utilizando o link http://www.valor.com.br/politica/5576627/duro-em-materias-penais-barroso-e-formador-de-maiorias-no-stf?origem=G1&utm_source=g1.globo.com&utm_medium=referral&utm_campaign=materia ou as ferramentas oferecidas na página. \n" +
                "Textos, fotos, artes e vídeos do Valor estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização do Valor (falecom@valor.com.br). Essas regras têm como objetivo proteger o investimento que o Valor faz na qualidade de seu jornalismo."
        comment1.idClient = 1L
        comment1.commentDate = "06/06/2018 as 17:08"
        comment1.idProfessional = 3L


        var comment2 = CommentsProfessionalResponse()
        comment2.photoClient = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvcOLHAwG03XZ8VWeOtVphHXQFd4-PP7ybiPpxcUBV7Z36aoZa"
        comment2.clientName = "Fernando Talles de Souza"
        comment2.comment = "Moreira Franco destaca os esforços do atual governo em alavancar a economia do país e critica governos anteriores. “Hoje eu tenho absoluta convicção de que não há mais nenhuma lembrança da época em que aritmética foi substituída pela ideologia” #g1economia."
        comment2.idClient = 1L
        comment2.commentDate = "01/06/2018 as 22:00"
        comment2.idProfessional = 3L


        var list = listOf(comment,comment1,comment2)
        recycleView?.adapter = ProfessionalCommentsAdapter(list,{comment : CommentsProfessionalResponse -> onCommentClick(comment)})
    }

    private fun onCommentClick(comment: CommentsProfessionalResponse){

    }

}// Required empty public constructor
