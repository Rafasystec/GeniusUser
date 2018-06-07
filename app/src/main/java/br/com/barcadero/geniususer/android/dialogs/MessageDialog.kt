package br.com.barcadero.geniususer.android.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.view.Window
import br.com.barcadero.geniususer.R
import kotlinx.android.synthetic.main.dialog_message.*
/**
 * Created by Rafael Rocha on 07/06/2018.
 */
class MessageDialog(context: Context) : Dialog(context), View.OnClickListener {

    var text: String? = null
    var title:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_message)
        if (tvMessageDialog != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                tvMessageDialog.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
            }
        }
        btnOK.setOnClickListener(this)
        tvMessageTitle.text = title
        tvMessageDialog.text = text
    }

    override fun onClick(view: View) {
        dismiss()
    }
}