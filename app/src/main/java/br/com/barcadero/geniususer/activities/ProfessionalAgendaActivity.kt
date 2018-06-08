package br.com.barcadero.geniususer.activities

import android.os.Bundle
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.fragments.ProfessionalAgendaFragment

class ProfessionalAgendaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_agenda)
        includeFragment(R.id.frameProfessionalAgenda,ProfessionalAgendaFragment())
    }
}
