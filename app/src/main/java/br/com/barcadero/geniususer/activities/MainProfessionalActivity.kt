package br.com.barcadero.geniususer.activities


import android.os.Bundle
import android.support.v4.app.Fragment
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.fragments.ProfessionalAgendaFragment
import br.com.barcadero.geniususer.fragments.ProfessionalHistoryFragment
import br.com.barcadero.geniususer.fragments.ProfessionalMoreFragment
import br.com.barcadero.geniususer.fragments.ProfessionalWorkDoneFragment
import kotlinx.android.synthetic.main.activity_main_professional.*


class MainProfessionalActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_professional)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.wellcome)
        includeFragment(R.id.frameMainProfessionalAgenda, ProfessionalAgendaFragment())
        btnNavigationPro.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuHome -> {
                    //includeFragment(R.id.frameMainProfessionalAgenda, ProfessionalAgendaFragment())
                    switchFragment(ProfessionalAgendaFragment())
                    true
                }
                R.id.menuHistory -> {
                    //includeFragment(R.id.frameMainProfessionalAgenda, ProfessionalHistoryFragment())
                    switchFragment(ProfessionalHistoryFragment())
                    true
                }
                R.id.menuMore -> {
                    //includeFragment(R.id.frameMainProfessionalAgenda, ProfessionalAgendaFragment())
                    switchFragment(ProfessionalMoreFragment())
                    true
                }
                R.id.menuMyWorks -> {
                    //includeFragment(R.id.frameMainProfessionalAgenda, ProfessionalWorkDoneFragment())
                    switchFragment(ProfessionalWorkDoneFragment())
                    true
                }
                else ->{ true}
            }

        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameMainProfessionalAgenda, fragment)
                .commit()
    }
}
