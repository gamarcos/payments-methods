package br.com.gabrielmarcos.mercadopago.network.interfaces

import br.com.gabrielmarcos.mercadopago.BuildConfig
import br.com.gabrielmarcos.mercadopago.models.PaymentsModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gabriel Marcos on 05/11/2018
 */
interface PaymentMethodsInterface {
    @GET(BuildConfig.URL_METHODS)
    fun getPaymentMethods(@Query("public_key") publicKey: String): Observable<ArrayList<PaymentsModel>>
}