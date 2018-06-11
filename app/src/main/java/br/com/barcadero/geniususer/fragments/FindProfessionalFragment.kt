package br.com.barcadero.geniususer.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.activities.CommentsToProfessionalActivity
import br.com.barcadero.geniususer.android.adapters.ProfessionalAdapter
import br.com.barcadero.geniususer.extensions.defaultRecycleView
import br.com.barcadero.geniususer.model.enums.EnumProfessionalArea
import br.com.barcadero.geniususer.model.responses.ProfessionalResponse
import br.com.barcadero.geniususer.util.PutExtraKeys
import br.com.transferr.extensions.log
import kotlinx.android.synthetic.main.fragment_find_professional.*


/**
 * A simple [Fragment] subclass.
 */
class FindProfessionalFragment : BaseFragment(){

    var recycleView: RecyclerView?=null
    var professionals = listOf<ProfessionalResponse>()
    var professionalArea:EnumProfessionalArea?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        getArgumentsParameter()
        return inflater.inflate(R.layout.fragment_find_professional, container, false)
    }

    private fun getArgumentsParameter() {
        var bundle = arguments
        if (bundle != null) {
            professionalArea = bundle?.get(PutExtraKeys.PROFESSIONAL_AREA) as EnumProfessionalArea
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("Professional area ${professionalArea?.ordinal}")
        recycleView = defaultRecycleView(view,R.id.rcFindProf)
        initView()
    }

    override fun onResume() {
        super.onResume()
        loadProfessionals()
    }

    private fun loadProfessionals(){
        var professional = ProfessionalResponse()
        professional.urlPhoto= "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtTG0j1MmEng29JZuTbH7KqM55WOrUD7XfxtzOseyZeuFWJPv7"
        professional.distance="5,6 km"
        professional.id=1L
        professional.name="Gilberto Lira"
        professional.value="R$ 80,00"
        professional.rate = 5
        professional.isFavorite = true

        var professional2 = ProfessionalResponse()
        professional2.urlPhoto= "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTD7u5Dj3Tz7p07cLDeN7uMR2sKdjfwEoUSEnexvV1p3RA95QPZAw"
        professional2.distance="6,5 Km"
        professional2.id=2L
        professional2.name="Luis Brito"
        professional2.value="R$ 150,00"
        professional2.rate = 3

        var professional3 = ProfessionalResponse()
        professional3.urlPhoto= "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSdL1s1tMcbRkQm2VhwJUVrKmFTAqm4FprzAtAMONpZVTkFjFPlA"
        professional3.distance="10,5 Km"
        professional3.id=3L
        professional3.name="Carlos Aires Brito"
        professional3.value="R$ 350,00"
        professional3.rate = 2

        var professional4 = ProfessionalResponse()
        professional4.urlPhoto= "https://media.glamour.com/photos/58f90f25510a907b04e2cfd9/master/w_1280,c_limit/meredithgolden.jpg"
        professional4.distance="4,6 Km"
        professional4.id=4L
        professional4.name="Elena Santiago"
        professional4.value="R$ 200,00"
        professional4.rate = 2

        var professional5 = ProfessionalResponse()
        professional5.urlPhoto= "http://redmondphotography.com/wp-content/uploads/2018/02/Parsons-710-800x600.jpg"
        professional5.distance="11,8 Km"
        professional5.id=5L
        professional5.name="Renata Almeida"
        professional5.value="R$ 200,00"
        professional5.rate = 0

        this.professionals = listOf(professional,professional2,professional3,professional4,professional5)
        this.recycleView?.adapter = ProfessionalAdapter(this.professionals,{professional: ProfessionalResponse -> startCommentActivity()})
    }

    private fun initView(){
        initSpinnerAvalicao()
        initSpinnerDistance()
        initSpinnerPrice()
    }

    private  fun initSpinnerPrice(){
        spPriceRange.adapter = ArrayAdapter<String>(activity,R.layout.support_simple_spinner_dropdown_item, listOf("50 a 150","150 a 250","250 a 300","+ 300,00"))
        spPriceRange.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //var value = position
                //Put the position
            }

        }
    }

    private fun initSpinnerAvalicao(){
        spClientRate.adapter = ArrayAdapter(activity,R.layout.support_simple_spinner_dropdown_item, listOf("Uma Estrela","Duas Estrelas","3 Estrelas","4 Estrelas","5 Estrelas"))
        spClientRate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //var value = position
                //Put the position
            }

        }
    }

    private fun initSpinnerDistance(){
        spDistance.adapter = ArrayAdapter(activity,R.layout.support_simple_spinner_dropdown_item, listOf("0 a 5 km","5 a 15 Km","15 a 30","+ de 30 Km"))
        spDistance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //var value = position
                //Put the position
            }

        }
    }

    fun startCommentActivity(){
        var intent = Intent(activity,CommentsToProfessionalActivity::class.java)
        startActivity(intent)
    }
}// Required empty public constructor
