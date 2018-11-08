package br.com.gabrielmarcos.mercadopago.utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import br.com.gabrielmarcos.mercadopago.R

/**
 * Created by Gabriel Marcos on 08/11/2018
 */

class CustomViews {
    companion object {
        fun alertDialog(context: Context,
                        title: String,
                        description: String,
                        buttonText: String,
                        buttonCallback: View.OnClickListener): Dialog {

            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_alert)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)

            val tvTitle = dialog.findViewById<TextView>(R.id.dialog_title)
            tvTitle.text = title

            val tvDescription = dialog.findViewById<TextView>(R.id.dialog_description)
            tvDescription.text = description

            val bButton = dialog.findViewById<Button>(R.id.dialog_button)
            bButton.text = buttonText

            bButton.setOnClickListener(buttonCallback)

            return dialog
        }
    }
}