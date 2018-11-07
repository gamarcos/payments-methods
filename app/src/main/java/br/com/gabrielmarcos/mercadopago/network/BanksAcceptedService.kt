package br.com.gabrielmarcos.mercadopago.network

import android.content.Context
import br.com.gabrielmarcos.mercadopago.BuildConfig
import br.com.gabrielmarcos.mercadopago.models.BankModel
import br.com.gabrielmarcos.mercadopago.network.helper.RetrofitHelper
import br.com.gabrielmarcos.mercadopago.network.interfaces.BanksAcceptedInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Gabriel Marcos on 07/11/2018
 */
class BanksAcceptedService(context: Context): BaseService(context) {

    private val bankList by lazy {
        RetrofitHelper.getRetrofit(context).create(BanksAcceptedInterface::class.java)
    }

    fun getBankList(bankId: String,
                     successCallBack: (response: ArrayList<BankModel>) -> Unit,
                     errorCallback: (error: String) -> Unit) {
        observable = bankList.getBanks(BuildConfig.PUBLIC_KEY, bankId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    successCallBack(response)
                },
                { error ->
                    errorCallback(error.toString())
                })
    }
}