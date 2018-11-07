package br.com.gabrielmarcos.mercadopago.presenter

import android.content.Context
import br.com.gabrielmarcos.mercadopago.network.RecommendationService
import br.com.gabrielmarcos.mercadopago.ui.recommendation.RecommendationContractView

/**
 * Created by Gabriel Marcos on 07/11/2018.
 */

class RecommendationPresenter(private val recommendationContractView: RecommendationContractView,
                              private val context: Context) {

    var recommendationService: RecommendationService? = null

    fun getPaymentsMethods(amount: String,
                           paymentMethod: String,
                           issuer: String) {

        recommendationService = RecommendationService(context)

        recommendationService!!.getPaymentsMethods(amount, paymentMethod, issuer, {
            recommendationContractView.setData(it.first().payerCosts)
        },{
            recommendationContractView.setDataError(it)
        })
    }
}