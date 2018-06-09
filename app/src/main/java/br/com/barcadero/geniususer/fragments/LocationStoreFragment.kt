package br.com.barcadero.geniususer.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.activities.LocationStoreActivity
import br.com.barcadero.geniususer.activities.LocationStoreListActivity
import br.com.barcadero.geniususer.activities.TestActivity
import kotlinx.android.synthetic.main.fragment_location_store.*


/**
 * A simple [Fragment] subclass.
 */
class LocationStoreFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cvNursery.setOnClickListener { startLocationStoreListActivity() }
        cvDrilling.setOnClickListener { startLocationStoreListActivity() }
        cvHairSalon.setOnClickListener { startLocationStoreListActivity() }
        cvPetShop.setOnClickListener { startLocationStoreListActivity() }
        cvWorkshop.setOnClickListener { startLocationStoreListActivity() }
    }

    private fun startLocationStoreListActivity(){
        startActivity(Intent(activity,LocationStoreListActivity::class.java))
    }

}
