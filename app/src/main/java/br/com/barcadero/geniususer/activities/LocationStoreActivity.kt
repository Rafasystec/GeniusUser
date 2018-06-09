package br.com.barcadero.geniususer.activities

import android.os.Bundle
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.fragments.LocationStoreFragment

class LocationStoreActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_store)
        includeFragment(R.id.frameLocationStore,LocationStoreFragment())
    }
}
