package br.com.gabrielmarcos.mercadopago.presenter

import android.content.Context
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.models.PaymentsModel
import br.com.gabrielmarcos.mercadopago.models.RecommendationsModel
import br.com.gabrielmarcos.mercadopago.network.RecommendationService
import br.com.gabrielmarcos.mercadopago.ui.recommendation.RecommendationContractView

/**
 * Created by Gabriel Marcos on 07/11/2018.
 */

class RecommendationPresenter(private val recommendationContractView: RecommendationContractView,
                              private val context: Context) {

    init {
        recommendationContractView.showProgress()
    }

    var recommendationService: RecommendationService? = null

    fun getPaymentsMethods(amount: String,
                           paymentMethod: String,
                           issuer: String) {

        recommendationService = RecommendationService(context)

        recommendationService!!.getPaymentsMethods(amount, paymentMethod, issuer, {
            recommendationContractView.setData(it.first().payerCosts)
            recommendationContractView.hideProgress()
            validateRequest(it)
        },{
            recommendationContractView.setDataError(it)
            recommendationContractView.hideProgress()
            recommendationContractView.showDialog(context.getString(R.string.dialog_default_error))
        })
    }

    private fun validateRequest(recommendations: ArrayList<RecommendationsModel>) {
        if (recommendations.isEmpty()) {
            recommendationContractView.showDialog(context.getString(R.string.dialog_not_found_recommendation))
        }
    }
}