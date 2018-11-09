package br.com.gabrielmarcos.mercadopago.ui.recommendation

import br.com.gabrielmarcos.mercadopago.models.RecommendedMessageModel
import br.com.gabrielmarcos.mercadopago.utils.BaseContract

/**
 * Created by Gabriel Marcos on 07/11/2018.
 */

interface RecommendationContractView: BaseContract {
    fun setData(recommendations: ArrayList<RecommendedMessageModel>)
}