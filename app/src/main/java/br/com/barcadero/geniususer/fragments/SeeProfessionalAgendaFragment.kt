package br.com.barcadero.geniususer.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.android.adapters.SeeProfessionalAgendaAdapter
import br.com.barcadero.geniususer.extensions.defaultRecycleView
import br.com.barcadero.geniususer.extensions.toast
import br.com.barcadero.geniususer.model.BaseEntity
import br.com.barcadero.geniususer.model.ProfessionalAgenda
import org.jetbrains.anko.alert


/**
 * A simple [Fragment] subclass.
 */
class SeeProfessionalAgendaFragment : Fragment() {


    var recycleView: RecyclerView?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_see_professional_agenda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView = defaultRecycleView(view,R.id.rcSeeAgendaProfessional)
    }

    override fun onResume() {
        super.onResume()
        loadProfessionalAgenda()
    }
    private fun loadProfessionalAgenda() {


        var agenda = ProfessionalAgenda()
        agenda.hour = "13:00"
        agenda.isBusy = false

        var agenda1 = ProfessionalAgenda()
        agenda1.hour = "14:00"
        agenda1.isBusy = false

        var agenda2 = ProfessionalAgenda()
        agenda2.hour = "15:00"
        agenda2.isBusy = true

        var agenda3 = ProfessionalAgenda()
        agenda3.hour = "16:00"
        agenda3.isBusy = false

        var agenda4 = ProfessionalAgenda()
        agenda4.hour = "17:00"
        agenda4.isBusy = true

        var agenda5 = ProfessionalAgenda()
        agenda5.hour = "18:00"
        agenda5.isBusy = false

        var agenda6 = ProfessionalAgenda()
        agenda6.hour = "19:00"
        agenda6.isBusy = false

        var list = listOf(agenda,agenda1,agenda2,agenda3,agenda4,agenda5,agenda6)
        recycleView?.adapter = SeeProfessionalAgendaAdapter(list,{ agenda : ProfessionalAgenda -> onAgendaClick(agenda)})
    }

    fun onAgendaClick(agenda:ProfessionalAgenda){
        activity!!.alert("Tem certeza que deseja agendar ${agenda.hour} ?","Confirme"){
            positiveButton("Sim") { onClickYes(agenda) }
            negativeButton("Não") { it.dismiss() }
        }.show()
    }
    /*
    fun onAgendaClick(agenda:ProfessionalAgenda){
        var confirmDlg = ConfirmDialog(activity!!,{onClickYes(it)})
        confirmDlg.title = "Confirme"
        confirmDlg.text = "Tem certeza que deseja agendar ${agenda.hour} ?"
        //confirmDlg.btnYes.setOnClickListener { onClickYes(agenda) }
        //confirmDlg.onClick(confirmDlg.btnYes)
        confirmDlg.show()
    }
    */

    fun onClickYes(agenda:BaseEntity){
        toast("Muito obrigado por confiar em nós.",activity!!)
    }

}
