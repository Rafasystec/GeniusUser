package br.com.barcadero.geniususer.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.fragments.FindProfessionalFragment
import br.com.barcadero.geniususer.model.enums.EnumProfessionalArea
import br.com.barcadero.geniususer.util.PutExtraKeys
import kotlinx.android.synthetic.main.activity_find_professional.*


class FindProfessionalActivity : BaseActivity() {

    var professionalArea:EnumProfessionalArea?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_professional)
        toolbar.title = resources.getString(R.string.choose_professional)
        setSupportActionBar(toolbar)
        //supportActionBar?.title = resources.getString(R.string.choose_professional)

        var bundle = intent.extras
        if(bundle != null){
            professionalArea = bundle?.get(PutExtraKeys.PROFESSIONAL_AREA) as EnumProfessionalArea
        }
        includeFragment()
    }

    private fun includeFragment(){

        //val fm = supportFragmentManager.beginTransaction()
        //val findProfessionalFragment = FindProfessionalFragment()
        val mArguments = Bundle()
        mArguments.putSerializable(PutExtraKeys.PROFESSIONAL_AREA,professionalArea)
        //findProfessionalFragment.arguments = mArguments
        //fm.add(R.id.fragFindProfessional,findProfessionalFragment,"Escolha um profissional")
        //fm.commit()
        includeFragment(R.id.fragFindProfessional,FindProfessionalFragment(),mArguments)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.find_professional_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_see_map -> return startMap()
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun startMap():Boolean{
        val intent = Intent(this,MapsActivity::class.java)
        startActivity(intent)
        return true
    }

}
