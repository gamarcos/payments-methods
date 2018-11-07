package br.com.gabrielmarcos.mercadopago.network.interfaces

import br.com.gabrielmarcos.mercadopago.BuildConfig
import br.com.gabrielmarcos.mercadopago.models.RecommendationsModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gabriel Marcos on 07/11/2018.
 */
interface RecommendationInterface {
    @GET(BuildConfig.URL_PAYMENT)
    fun getRecommendations(@Query("public_key") publicKey: String,
                           @Query("amount") amount: String,
                           @Query("payment_method_id") paymentId: String,
                           @Query("issuer.id") issuer: String): Observable<ArrayList<RecommendationsModel>>
}