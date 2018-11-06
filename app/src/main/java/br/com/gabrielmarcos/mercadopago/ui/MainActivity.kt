package br.com.gabrielmarcos.mercadopago.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.models.PaymentsModel
import br.com.gabrielmarcos.mercadopago.presenter.PaymentMethodsPresenter
import br.com.gabrielmarcos.mercadopago.ui.paymentsMethods.PaymentMethodsAdapter
import br.com.gabrielmarcos.mercadopago.ui.paymentsMethods.PaymentMethodsViewContract
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    PaymentMethodsViewContract {

    private lateinit var paymentMethodsPresenter: PaymentMethodsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paymentMethodsPresenter = PaymentMethodsPresenter(this,this)

        paymentMethodsPresenter.getPaymentsMethods()

        setupButtons()
    }

    override fun showProgress() {}

    override fun hideProgress() {}

    override fun setDataError(strError: String) { print(strError) }

    override fun setData(paymentMethods: ArrayList<PaymentsModel>) {
        setupListPaymentsMethods(paymentMethods)
    }

    private fun setupListPaymentsMethods(paymentMethods: ArrayList<PaymentsModel>) {
        methodsPaymentList.adapter =
                PaymentMethodsAdapter(this, paymentMethods)
        methodsPaymentList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupButtons() {
        nextButton
    }

}
