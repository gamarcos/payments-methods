package br.com.gabrielmarcos.mercadopago.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Gabriel Marcos on 07/11/2018.
 */
data class RecommendationsModel(@SerializedName("payer_costs") val payerCosts: ArrayList<RecommendedMessageModel>)