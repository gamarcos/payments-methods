package br.com.gabrielmarcos.mercadopago.ui.paymentsMethods

import br.com.gabrielmarcos.mercadopago.models.PaymentsModel
import br.com.gabrielmarcos.mercadopago.utils.BaseContract

/**
 * Created by Gabriel Marcos on 06/11/2018.
 */

interface PaymentMethodsViewContract: BaseContract {
    fun setData(paymentMethods: ArrayList<PaymentsModel>)
}
