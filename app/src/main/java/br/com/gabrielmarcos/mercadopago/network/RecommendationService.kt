package br.com.gabrielmarcos.mercadopago.network

import android.content.Context
import br.com.gabrielmarcos.mercadopago.BuildConfig
import br.com.gabrielmarcos.mercadopago.models.RecommendationsModel
import br.com.gabrielmarcos.mercadopago.network.helper.RetrofitHelper
import br.com.gabrielmarcos.mercadopago.network.interfaces.RecommendationInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Gabriel Marcos on 07/11/2018.
 */
class RecommendationService(context: Context): BaseService(context) {

    private val recommendations by lazy {
        RetrofitHelper.getRetrofit(context).create(RecommendationInterface::class.java)
    }

    fun getPaymentsMethods(amount: String,
                           paymentMethod: String,
                           issuer: String,
                           successCallBack: (response: ArrayList<RecommendationsModel>) -> Unit,
                           errorCallback: (error: String) -> Unit) {
        observable = recommendations.getRecommendations(BuildConfig.PUBLIC_KEY, amount, paymentMethod, issuer)
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