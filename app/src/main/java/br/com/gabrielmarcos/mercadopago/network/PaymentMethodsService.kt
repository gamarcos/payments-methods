package br.com.gabrielmarcos.mercadopago.network

import android.content.Context
import br.com.gabrielmarcos.mercadopago.BuildConfig
import br.com.gabrielmarcos.mercadopago.models.PaymentsModel
import br.com.gabrielmarcos.mercadopago.network.helper.RetrofitHelper
import br.com.gabrielmarcos.mercadopago.network.interfaces.PaymentMethodsInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Gabriel Marcos on 04/11/2018
 */
class PaymentMethodsService(context: Context): BaseService(context) {

    private val paymentsMethods by lazy {
        RetrofitHelper.getRetrofit(context).create(PaymentMethodsInterface::class.java)
    }

    fun getPaymentsMethods(successCallBack: (response: ArrayList<PaymentsModel>) -> Unit,
                           errorCallback: (error: String) -> Unit) {

        observable = paymentsMethods.getPaymentMethods(BuildConfig.PUBLIC_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    successCallBack(response)
                },
                { error ->
                    errorCallback(error.toString())
                })
    }

}
