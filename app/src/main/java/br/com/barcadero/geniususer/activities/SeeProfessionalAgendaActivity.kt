package br.com.barcadero.geniususer.activities

import android.app.Activity
import android.os.Bundle
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.fragments.SeeProfessionalAgendaFragment

class SeeProfessionalAgendaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_professional_agenda)
        includeFragment(R.id.frameProfessionalAgenda,SeeProfessionalAgendaFragment())
    }
}
