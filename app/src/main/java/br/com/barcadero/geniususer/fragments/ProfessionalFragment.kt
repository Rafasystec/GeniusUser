package br.com.barcadero.geniususer.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.model.enums.EnumSexo
import br.com.barcadero.geniususer.util.TextWatcherUtil
import kotlinx.android.synthetic.main.basic_profile_layout.*
import kotlinx.android.synthetic.main.fragment_professional.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProfessionalFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * @author Rafael Rocha on 06/06/2018
 */
class ProfessionalFragment : BaseFragment() {

    var gender:EnumSexo?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_professional, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        initSpinnerProfessionalArea()
        initSpinnerProfessionalAttendanceType()
        initSpinnerProfessionalEducationDegree()
        initSpinnerProfessionalPriceType()
        initSpinnerSexo()
        initFields()
    }

    private fun initFields(){
        etNameProfile.addTextChangedListener(TextWatcherUtil(60,etBirthDateDay))
        etBirthDateDay.addTextChangedListener(TextWatcherUtil(2,etBirthDateMonth))
        etBirthDateMonth.addTextChangedListener(TextWatcherUtil(2,etBirthDateYear))
        fabSaveProfessional.requestFocus()
        etNameProfile.setOnFocusChangeListener { view,fuscused ->
            if(fuscused) {
                hideSoftKeyboard()
            }
        }

    }

    private fun initSpinnerProfessionalPriceType(){
        spProfessionalPriceType.adapter = ArrayAdapter(activity,R.layout.support_simple_spinner_dropdown_item, listOf("Por Hora","Por Dia","Orçamento"))
        spProfessionalPriceType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //var value = position
                //Put the position
            }

        }
    }

    private fun initSpinnerProfessionalAttendanceType(){
        spProfessionalAttendanceType.adapter = ArrayAdapter(activity,R.layout.support_simple_spinner_dropdown_item, listOf("Atendo em Casa","Atendo no meu Local","Ambos"))
        spProfessionalAttendanceType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               // var value = position
                //Put the position
            }

        }
    }

    private fun initSpinnerProfessionalEducationDegree(){
        spProfessionalEducationDegree.adapter = ArrayAdapter(activity,R.layout.support_simple_spinner_dropdown_item, listOf("Ensino Fundamento","Ensino Médio","Superior Incompleto","Superior Completo","Mestre ou Mestrando","Doutorado"))
        spProfessionalEducationDegree.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //var value = position
                //Put the position
            }
        }
    }
    private fun initSpinnerProfessionalArea(){
        spProfessionalArea.adapter = ArrayAdapter(activity,R.layout.support_simple_spinner_dropdown_item, listOf("Ar condicionado",
                "Auvenaria e Acabamento",
                "Diarista",
                "Eletrica",
                "Marcenaria",
                "Mecânica e Funilaria",
                "Pintura"))
        spProfessionalArea.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //var value = position
                //Put the position
            }
        }
    }

    private fun initSpinnerSexo(){
        spSexoBasic.adapter = ArrayAdapter(activity,R.layout.support_simple_spinner_dropdown_item, listOf("Masculino","Feminino"))
        spSexoBasic.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                gender = if(position == 0){
                    EnumSexo.MALE
                }else{
                    EnumSexo.FEMALE
                }
            }
        }
    }

    fun hideSoftKeyboard() {
        if (activity?.currentFocus != null) {
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus!!.windowToken, 0)
        }
    }




}// Required empty public constructor
