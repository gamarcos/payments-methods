package br.com.gabrielmarcos.mercadopago.utils

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Gabriel Marcos on 06/11/2018.
 */
class PicassoServiceHelper(val context: Context?) {

    fun loadImage(url: String?, view: ImageView) {
        Picasso.with(context)
            .load(url)
            .into(view)
    }
}