package br.com.gabrielmarcos.mercadopago.network

import android.content.Context
import io.reactivex.disposables.Disposable

/**
 * Created by Gabriel Marcos on 05/11/2018
 */
open class BaseService(val context: Context?) {

    var observable: Disposable? = null

    fun unsubscribe() {
        observable?.dispose()
    }
}