package br.com.gabrielmarcos.mercadopago.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Gabriel Marcos on 04/11/2018
 */
data class PaymentsModel(val id: String,
                         val name: String,
                         @SerializedName("payment_type_id")
                         val paymentTypeId: String,
                         val status: String,
                         @SerializedName("secure_thumbnail")
                         val secureThumbnail: String,
                         val thumbnail: String,
                         @SerializedName("deferred_capture")
                         val deferredCapture: String)
