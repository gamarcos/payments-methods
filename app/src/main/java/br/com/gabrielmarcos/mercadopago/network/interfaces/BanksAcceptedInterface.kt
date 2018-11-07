package br.com.gabrielmarcos.mercadopago.network.interfaces

import br.com.gabrielmarcos.mercadopago.BuildConfig
import br.com.gabrielmarcos.mercadopago.models.BankModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gabriel Marcos on 07/11/2018
 */
interface BanksAcceptedInterface {
    @GET(BuildConfig.URL_BANKS)
    fun getBanks(@Query("public_key") publicKey: String,
                 @Query("payment_method_id") paymentId: String): Observable<ArrayList<BankModel>>
}