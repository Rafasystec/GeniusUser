package br.com.barcadero.geniususer.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.barcadero.geniususer.R
import com.google.zxing.WriterException
import android.graphics.Bitmap
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix



class ClientAgendaDetailsActivity : AppCompatActivity() {

    val WHITE = -0x1
    val BLACK = -0x1000000
    val WIDTH = 400
    val HEIGHT = 400
    val STR = "A string to be encoded as QR code"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_agenda_details)
        val imageView = findViewById<ImageView>(R.id.ivQrCode)
        try {
            val bitmap = encodeAsBitmap(STR)
            imageView.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }

/*
        btnGenerateQrCode.setOnClickListener {
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
        */
    }

    @Throws(WriterException::class)
    fun encodeAsBitmap(str: String): Bitmap? {
        val result: BitMatrix
        try {
            result = MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null)
        } catch (iae: IllegalArgumentException) {
            // Unsupported format
            return null
        }

        val width = result.width
        val height = result.height
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (result.get(x, y)) BLACK else WHITE
            }
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }
/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(result != null){
            if(result.contents == null){
                toast("Canceled",this)
            }else{
                toast("Scaned ${result.contents}",this)
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
*/

}
