package br.com.barcadero.geniususer.activities

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.util.ShowCaseViewUtil
import br.com.transferr.extensions.hideKeyBoard

class ProfessionalActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional)
        showCaseFabSave()
        hideSoftKeyboard()
    }

    private fun showCaseFabSave(){
        //ShowCaseViewUtil.singleShot(R.id.fabSaveProfessional, this,"Para salvar ou atualizar os seus dados","Salvar as alterações",resources)
        showCaseOnView(R.id.fabSaveProfessional,"Salvar as alterações","Para salvar ou atualizar os seus dados")
        //hideKeyBoard()
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager!!.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
}
