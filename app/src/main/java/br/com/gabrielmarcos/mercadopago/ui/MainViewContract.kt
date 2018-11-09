package br.com.gabrielmarcos.mercadopago.ui


/**
 * Created by Gabriel Marcos on 09/11/2018.
 */
interface MainViewContract {
    fun showProgress()
    fun hideProgress()
    fun resetFragment()
    fun showDialog(message: String)

    fun getBank(): String
    fun getBanner(): String
    fun getAmount() : String
    fun setBank(bankValue: String)
    fun setBanner(bannerValue: String)
    fun setAmount(amountValue: String)
}