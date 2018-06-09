package br.com.barcadero.geniususer.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.android.adapters.LocationStoreAdapter
import br.com.barcadero.geniususer.model.responses.LocationStoreResponse

class LocationStoreListActivity : BaseActivity() {

    var recycleView:RecyclerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_store_list)
        //recycleView = defaultRecycleView(R.id.rcLocalStore)
    }

    override fun onCreateView(parent: View?, name: String?, context: Context?, attrs: AttributeSet?): View {

        return super.onCreateView(parent, name, context, attrs)
    }

    override fun onResume() {
        super.onResume()
        loadLocationsStore()
    }

    private fun loadLocationsStore(){
        var store = LocationStoreResponse()
        with(store){
            storeName = "Borracharia o DEDE"
            distance = "3 km"
            address = "Rua Jardim 9 - Araturi Caucaia"
            rate = "3"
            urlPhoto = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4a2I6oS4Ui6uOfceBB94XQQcCD39sCbVTaZ9mbyLAIA9be6uYdw"
        }
        var store2 = LocationStoreResponse()
        with(store2){
            storeName = "Marcelo Pneu"
            distance = "4,5 km"
            address = "Rua Jardim America - Araturi Caucaia"
            rate = "3"
            urlPhoto = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4Rc4HVOti_1LO4etX4Xam5BnZK7W_1xFigUphTy9Bx-t9YgCHWg"
        }

        var store3 = LocationStoreResponse()
        with(store3){
            storeName = "O Mineiro"
            distance = "11,6 km"
            address = "Rua 11 de Maio - Araturi Caucaia"
            rate = "3"
            urlPhoto = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSv2nqz2PawjFoMjT0749BysMv4aNJ9ij6sP8BM4o9wskXkcBJXSw"
        }


        recycleView?.adapter = LocationStoreAdapter(listOf(store,store2,store3),{locationStoreResponse ->  startLocalStoreDetails(locationStoreResponse)})
    }

    private fun startLocalStoreDetails(store:LocationStoreResponse){

    }
}
