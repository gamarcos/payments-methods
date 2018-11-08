package br.com.gabrielmarcos.mercadopago.presenter

import android.content.Context
import br.com.gabrielmarcos.mercadopago.ui.paymentsMethods.PaymentMethodsViewContract
import br.com.gabrielmarcos.mercadopago.network.PaymentMethodsService

/**
 * Created by Gabriel Marcos on 06/11/2018.
 */
class PaymentMethodsPresenter(private val paymentMethodsViewContract: PaymentMethodsViewContract,
                              private val context: Context) {

    init {
        paymentMethodsViewContract.showProgress()
    }

    var paymentMethodsService: PaymentMethodsService? = null

    fun getPaymentsMethods() {
        paymentMethodsService = PaymentMethodsService(context)

        paymentMethodsService!!.getPaymentsMethods({
            paymentMethodsViewContract.setData(it)
            paymentMethodsViewContract.hideProgress()

        },{
            paymentMethodsViewContract.setDataError(it)
            paymentMethodsViewContract.hideProgress()
        })
    }
}