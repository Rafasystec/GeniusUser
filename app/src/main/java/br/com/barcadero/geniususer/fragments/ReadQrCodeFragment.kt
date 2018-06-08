package br.com.barcadero.geniususer.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.barcadero.geniususer.R
import br.com.barcadero.geniususer.extensions.toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_read_qr_code.*


/**
 * A simple [Fragment] subclass.
 * It is not working properly
 */
class ReadQrCodeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read_qr_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnReaQrCodeFragment.setOnClickListener {
            var intentIntegrator = IntentIntegrator(activity!!)
            with(intentIntegrator){
                setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                setPrompt("Aproxime o celular do QR-Code")
                setCameraId(0)
                setBeepEnabled(true)
                setBarcodeImageEnabled(false)
                setTargetFragment(this@ReadQrCodeFragment,1)
                initiateScan()
            }

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(result != null){
            if(result.contents == null){
                toast("Canceled",activity!!)
            }else{
                toast("Scaned ${result.contents}",activity!!)
                tvReadQrCodeResultFrame.text = result.contents
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}// Required empty public constructor
