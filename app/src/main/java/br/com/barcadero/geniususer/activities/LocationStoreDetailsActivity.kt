package br.com.barcadero.geniususer.activities

import android.os.Bundle
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.fragments.LocationStoreDetailFragment

class LocationStoreDetailsActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_store_details)
        includeFragment(R.id.frameLocalStoreDetail,LocationStoreDetailFragment())

    }




}
