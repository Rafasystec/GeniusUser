package br.com.barcadero.geniususer.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.fragments.ChooseServiceAreaFragment
import br.com.barcadero.geniususer.model.enums.EnumTypeUser
import br.com.transferr.extensions.toast
import br.com.transferr.util.Prefes
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    //var typeUser = EnumTypeUser.PROFESSIONAL
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Algum problema ou sujestão?", Snackbar.LENGTH_LONG)
                    .setAction("Sim", {startContactUsActivity()})
                    .setActionTextColor(ContextCompat.getColor(this,R.color.snackBarActionColor))
                    .show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        includeFragment()
        //Prefes.prefsTypeUser = EnumTypeUser.PROFESSIONAL
        //typeUser = Prefes.prefsTypeUser
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return startMenuStoreLocationActivity()
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                startProfileActivity()
            }
            R.id.nav_my_agenda -> {
                startMyAgendaClientActivity()
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {
                startReadQRCodeActivity()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun startProfileActivity(){
        startActivity(Intent(this, ClientActivity::class.java))
    }

    private fun includeFragment(){
        val fm = supportFragmentManager.beginTransaction()
        val choose = ChooseServiceAreaFragment()
        fm.add(R.id.mainFragment,choose,"Escolha uma Área")
        fm.commit()
    }

    private fun startContactUsActivity(){
        val intent = Intent(this,ContactActivity::class.java)
        startActivity(intent)
    }

    private fun startMyAgendaClientActivity(){
        val intent = Intent(this,ClientAgendaActivity::class.java)
        startActivity(intent)
    }

    private fun startReadQRCodeActivity(){
        var intent = Intent(this,ReadQRCodeActivity::class.java)
        startActivity(intent)
    }

    private fun startMenuStoreLocationActivity():Boolean{
        startActivity(Intent(this,LocationStoreActivity::class.java))
        return true
    }

}
