package br.com.gabrielmarcos.mercadopago.utils

/**
 * Created by Gabriel Marcos on 06/11/2018.
 */

interface BaseContract {
    fun setDataError(strError: String)
    fun showProgress()
    fun hideProgress()
    fun validateFields(): Boolean
}