package br.com.gabrielmarcos.mercadopago.ui.paymentsMethods

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.models.PaymentsModel
import br.com.gabrielmarcos.mercadopago.presenter.PaymentMethodsPresenter
import br.com.gabrielmarcos.mercadopago.utils.RadioAdapter
import kotlinx.android.synthetic.main.fragment_methods_payments.*

/**
 * Created by Gabriel Marcos on 06/11/2018.
 */

class PaymentMethodsFragment: Fragment(), PaymentMethodsViewContract, RadioAdapter.RadioAdapterListener {

    private lateinit var paymentMethodsPresenter: PaymentMethodsPresenter
    private lateinit var paymentMethodsList: ArrayList<PaymentsModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_methods_payments, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paymentMethodsPresenter = PaymentMethodsPresenter(this@PaymentMethodsFragment,context!!)

        paymentMethodsPresenter.getPaymentsMethods()

    }

    override fun setData(paymentMethods: ArrayList<PaymentsModel>) {
        paymentMethodsList = paymentMethods
        setupListPaymentsMethods()
    }

    override fun setDataError(strError: String) {
        Toast.makeText(context, strError, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {}

    override fun hideProgress() {}

    override fun radioChange(index: Int) {
    }

    private fun setupListPaymentsMethods() {
        methodsPaymentList.adapter = PaymentMethodsAdapter(context!!, paymentMethodsList, this@PaymentMethodsFragment)
        methodsPaymentList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}