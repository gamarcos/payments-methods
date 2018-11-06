package br.com.gabrielmarcos.mercadopago.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Gabriel Marcos on 04/11/2018
 */
data class BankModel(val id: String,
                     val name: String,
                     @SerializedName("secure_thumbnail")
                     val secureThumbnail: String,
                     val thumbnail: String,
                     @SerializedName("processing_mode")
                     val processing_mode: String,
                     @SerializedName("merchant_account_id")
                     val merchantAccountId: String?)