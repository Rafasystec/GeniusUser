package br.com.barcadero.geniususer.activities

import android.content.Intent
import android.os.Bundle
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.extensions.toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_read_qrcode.*

class ReadQRCodeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_qrcode)
        btnReaQrCode.setOnClickListener {
            var intentIntegrator = IntentIntegrator(this)
            with(intentIntegrator){
                setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                setPrompt("OK you read")
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
                tvReadQrCodeResult.text = result.contents
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
