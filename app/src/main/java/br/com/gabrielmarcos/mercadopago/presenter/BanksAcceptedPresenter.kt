package br.com.gabrielmarcos.mercadopago.presenter

import android.content.Context
import br.com.gabrielmarcos.mercadopago.models.BankModel
import br.com.gabrielmarcos.mercadopago.network.BanksAcceptedService
import br.com.gabrielmarcos.mercadopago.ui.banksAccepted.BanksAcceptedViewContract

/**
 * Created by Gabriel Marcos on 07/11/2018
 */

class BanksAcceptedPresenter(private val banksAcceptedViewContract: BanksAcceptedViewContract,
                             private val context: Context) {

    init {
        banksAcceptedViewContract.showProgress()
    }

    var bankService: BanksAcceptedService? = null

    fun getBankList(bankId: String) {
        bankService = BanksAcceptedService(context)

        bankService!!.getBankList(bankId, {
            banksAcceptedViewContract.setData(it)
            banksAcceptedViewContract.hideProgress()
            validateRequest(it)
        }, {
            banksAcceptedViewContract.setDataError(it)
            banksAcceptedViewContract.hideProgress()
            banksAcceptedViewContract.showDialog(it)
        })
    }

    private fun validateRequest(banks: ArrayList<BankModel>) {
        if (banks.isEmpty()) {
            banksAcceptedViewContract.showDialog("Não foi possivel encontrar nenhum banco no momento, tente mais tarde")
        }
    }
}