package br.com.gabrielmarcos.mercadopago.utils

import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import br.com.gabrielmarcos.mercadopago.R
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

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

        fun onTextChangedListener(editText: EditText): TextWatcher {
            return object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable) {
                    editText.removeTextChangedListener(this)

                    try {
                        var originalString = s.toString()

                        val longval: Long?
                        if (originalString.contains(",")) {
                            originalString = originalString.replace(",".toRegex(), "")
                        }
                        longval = java.lang.Long.parseLong(originalString)

                        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
                        formatter.applyPattern("#,###,###,###")
                        val formattedString = formatter.format(longval)

                        //setting text after format to EditText
                        editText.setText(formattedString)
                        editText.setSelection(editText.text.length)
                    } catch (nfe: NumberFormatException) {
                        nfe.printStackTrace()
                    }

                    editText.addTextChangedListener(this)
                }
            }
        }

    }
}