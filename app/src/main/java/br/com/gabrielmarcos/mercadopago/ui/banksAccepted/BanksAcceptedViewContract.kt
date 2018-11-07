package br.com.gabrielmarcos.mercadopago.ui.banksAccepted

import br.com.gabrielmarcos.mercadopago.models.BankModel
import br.com.gabrielmarcos.mercadopago.utils.BaseContract

/**
 * Created by Gabriel Marcos on 07/11/2018
 */
interface BanksAcceptedViewContract: BaseContract {
    fun setData(paymentMethods: ArrayList<BankModel>)
}