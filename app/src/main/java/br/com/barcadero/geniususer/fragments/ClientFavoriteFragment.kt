package br.com.barcadero.geniususer.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.android.adapters.ClientFavoriteAdapter
import br.com.barcadero.geniususer.extensions.defaultRecycleView
import br.com.barcadero.geniususer.model.enums.EnumProfessionalArea
import br.com.barcadero.geniususer.model.responses.ClientFavoriteResponse


/**
 * A simple [Fragment] subclass.
 */
class ClientFavoriteFragment : BaseFragment() {


    var recycleView:RecyclerView?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setTitleBarForClient(R.string.title_activity_client_favorite)
        return inflater.inflate(R.layout.fragment_client_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView = defaultRecycleView(view,R.id.rcClientFavorite)
    }

    override fun onResume() {
        super.onResume()
        loadClientFavorites()
    }
    fun loadClientFavorites(){
        var professional = ClientFavoriteResponse()
        with(professional){
            professionalName = "Arnaldo lira"
            urlPhotoPro=""
            currentRate=3
            isFavorite=true
            value="430,50"
            enumArea = EnumProfessionalArea.BRICKWORK
        }

        var professional2 = ClientFavoriteResponse()
        with(professional2){
            professionalName = "Vinicius campos sales"
            urlPhotoPro=""
            currentRate=3
            isFavorite=true
            value="80,50"
            enumArea = EnumProfessionalArea.MECHANICS
        }

        var professional3 = ClientFavoriteResponse()
        with(professional3){
            professionalName = "Odonis Paiva"
            urlPhotoPro=""
            currentRate=3
            isFavorite=true
            value="80,50"
            enumArea = EnumProfessionalArea.MECHANICS
        }

        var professional4 = ClientFavoriteResponse()
        with(professional4){
            professionalName = "Claci Ramos"
            urlPhotoPro=""
            currentRate=3
            isFavorite=true
            value="180,00"
            enumArea = EnumProfessionalArea.AIR_CONDITIONING
        }

        var professional5 = ClientFavoriteResponse()
        with(professional5){
            professionalName = "Maria Lucci"
            urlPhotoPro=""
            currentRate=4
            isFavorite=true
            value="115,00"
            enumArea = EnumProfessionalArea.DIARIST
        }

        var professional6 = ClientFavoriteResponse()
        with(professional6){
            professionalName = "Any Almeida"
            urlPhotoPro=""
            currentRate=5
            isFavorite=true
            value="100,00"
            enumArea = EnumProfessionalArea.PAINTING
        }
        recycleView?.adapter = ClientFavoriteAdapter(listOf(professional,professional2,professional3,professional4,professional5,professional6),{professional:ClientFavoriteResponse -> onClickView(professional)})
    }

    fun onClickView(professional:ClientFavoriteResponse){

    }

}// Required empty public constructor
