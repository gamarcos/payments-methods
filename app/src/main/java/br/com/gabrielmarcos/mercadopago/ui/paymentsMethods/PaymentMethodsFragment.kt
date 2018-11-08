package br.com.gabrielmarcos.mercadopago.ui.paymentsMethods

import android.content.Context
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
import br.com.gabrielmarcos.mercadopago.ui.MainActivity
import br.com.gabrielmarcos.mercadopago.utils.OnCommunicateInterface
import br.com.gabrielmarcos.mercadopago.utils.RadioAdapter
import kotlinx.android.synthetic.main.fragment_methods_payments.*
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat


/**
 * Created by Gabriel Marcos on 06/11/2018.
 */

class PaymentMethodsFragment: Fragment(), PaymentMethodsViewContract, RadioAdapter.RadioAdapterListener {

    private var bannerSelected = ""

    private lateinit var paymentMethodsPresenter: PaymentMethodsPresenter
    private lateinit var paymentMethodsList: ArrayList<PaymentsModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_methods_payments, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paymentMethodsPresenter = PaymentMethodsPresenter(this@PaymentMethodsFragment,context!!)

        paymentMethodsPresenter.getPaymentsMethods()

        setupView()
    }

    override fun setData(paymentMethods: ArrayList<PaymentsModel>) {
        paymentMethodsList = paymentMethods
        if (paymentMethodsList.isEmpty()) {
            setDataError("Não foi possivel encontrar nenhum banco no momento, tente mais tarde")
        } else {
            setupListPaymentsMethods()
        }
    }

    override fun setDataError(strError: String) {
        Toast.makeText(context, strError, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        (activity as MainActivity).showProgress()
    }

    override fun hideProgress() {
        (activity as MainActivity).hideProgress()
    }

    override fun radioChange(index: Int) {
        bannerSelected = paymentMethodsList[index].id
        MainActivity.banner = bannerSelected
    }

    override fun validateFields(): Boolean {
        return bannerSelected.isNotBlank() && amountValueEdt.text.toString().isNotBlank()
    }

    private fun setupView() {
        amountValueEdt.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                MainActivity.amount = amountValueEdt.text.toString()
            }
        })
    }

    private fun setupListPaymentsMethods() {
        methodsPaymentList.adapter = PaymentMethodsAdapter(context!!, paymentMethodsList, this@PaymentMethodsFragment)
        methodsPaymentList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}