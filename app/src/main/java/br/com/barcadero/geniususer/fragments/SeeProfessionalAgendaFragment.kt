package br.com.barcadero.geniususer.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.android.adapters.SeeProfessionalAgendaAdapter
import br.com.barcadero.geniususer.extensions.defaultRecycleView
import br.com.barcadero.geniususer.extensions.toast
import br.com.barcadero.geniususer.model.BaseEntity
import br.com.barcadero.geniususer.model.ProfessionalAgenda
import kotlinx.android.synthetic.main.fragment_see_professional_agenda.*
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
        initSpinnerAgendaDay()
        fabHelpAgenda.setOnClickListener{
            toast("Ajuda a caminho",activity!!)
        }
    }

    override fun onResume() {
        super.onResume()
        loadProfessionalAgenda()
    }
    private fun loadProfessionalAgenda() {


        var agenda = ProfessionalAgenda()
        agenda.hour = "08:00"
        agenda.isBusy = false

        var agenda1 = ProfessionalAgenda()
        agenda1.hour = "11:00"
        agenda1.isBusy = false

        var agenda2 = ProfessionalAgenda()
        agenda2.hour = "14:00"
        agenda2.isBusy = true

        var agenda3 = ProfessionalAgenda()
        agenda3.hour = "17:00"
        agenda3.isBusy = false

        var agenda4 = ProfessionalAgenda()
        agenda4.hour = "20:00"
        agenda4.isBusy = true

        var list = listOf(agenda,agenda1,agenda2,agenda3,agenda4)
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

    private  fun initSpinnerAgendaDay(){
        spChooseADay.adapter = ArrayAdapter<String>(activity,R.layout.support_simple_spinner_dropdown_item, listOf("Hoje","Amanhã","Depois de amanhã"))
        spChooseADay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //var value = position
                //Put the position
            }

        }
    }

}
