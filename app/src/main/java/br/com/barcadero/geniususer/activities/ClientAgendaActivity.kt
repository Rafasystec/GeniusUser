package br.com.barcadero.geniususer.activities

import android.os.Bundle
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.fragments.ClientAgendaFragment

class ClientAgendaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_agenda)
        includeFragment(R.id.frameClientAgenda,ClientAgendaFragment())
    }
}
