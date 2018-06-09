package br.com.barcadero.geniususer.activities

import android.content.Intent
import android.os.Bundle
import br.com.barcadero.geniususer.R
import kotlinx.android.synthetic.main.activity_main_decision.*

class MainDecisionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_decision)
        ivDecisionProfessional.setOnClickListener {startMainProfessionalActivity()}
        ivDecisionClient.setOnClickListener { startMainClientActivity() }
    }

    fun startMainProfessionalActivity(){
        startActivity(Intent(this,MainProfessionalActivity::class.java))
    }
    fun startMainClientActivity(){
        startActivity(Intent(this,MainActivity::class.java))
    }
}
