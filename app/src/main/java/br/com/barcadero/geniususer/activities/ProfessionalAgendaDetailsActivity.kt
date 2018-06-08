package br.com.barcadero.geniususer.activities

import android.content.Intent
import android.os.Bundle
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.extensions.toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_professional_agenda_details.*

class ProfessionalAgendaDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_agenda_details)
        setOnClickReadQrCode()
    }

    fun setOnClickReadQrCode(){
        btnReaQrCodeProf.setOnClickListener {
            var intentIntegrator = IntentIntegrator(this)
            with(intentIntegrator){
                setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                setPrompt("Aproxime o celular do QR-Code")
                setCameraId(0)
                setBeepEnabled(true)
                setBarcodeImageEnabled(false)
                initiateScan()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(result != null){
            if(result.contents == null){
                toast("Canceled",this)
            }else{
                toast("Scaned ${result.contents}",this)
                tvReadQrCodeResultProf.text = result.contents
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
