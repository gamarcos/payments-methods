package br.com.gabrielmarcos.mercadopago.presenter

import br.com.gabrielmarcos.mercadopago.ui.MainViewContract

/**
 * Created by Gabriel Marcos on 09/11/2018.
 */

class MainPresenter(private val mainViewContract: MainViewContract) {

    fun showDialog(message: String) {
        mainViewContract.showDialog(message)
    }

    fun hideProgress() {
        mainViewContract.hideProgress()
    }

    fun showProgress() {
        mainViewContract.showProgress()
    }

    fun getBank(): String {
        return mainViewContract.getBank()
    }

    fun getBanner(): String {
        return mainViewContract.getBanner()
    }

    fun getAmount() : String {
        return mainViewContract.getAmount()
    }

    fun setBank(bankValue: String) {
        mainViewContract.setBank(bankValue)
    }

    fun setBanner(bannerValue: String) {
        mainViewContract.setBanner(bannerValue)
    }

    fun setAmount(amountValue: String) {
        mainViewContract.setAmount(amountValue)
    }
}