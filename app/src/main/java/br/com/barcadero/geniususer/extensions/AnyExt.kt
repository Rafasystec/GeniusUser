package br.com.barcadero.geniususer.extensions

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import br.com.barcadero.geniususer.application.SystemApplication
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

/**
 * Created by Rafael Rocha on 07/06/2018 at 14:13.
 */
fun toast(message: CharSequence) {
    Toast.makeText(SystemApplication.getInstance().applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun toast(message: CharSequence, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Throws(WriterException::class)
fun encodeStringAsQrCode(str: String): Bitmap? {
    val WHITE = -0x1
    val BLACK = -0x1000000
    val WIDTH = 400
    val HEIGHT = 400
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