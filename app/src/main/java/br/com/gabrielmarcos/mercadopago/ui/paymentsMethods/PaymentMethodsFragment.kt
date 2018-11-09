package br.com.gabrielmarcos.mercadopago.ui.paymentsMethods

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.models.PaymentsModel
import br.com.gabrielmarcos.mercadopago.presenter.PaymentMethodsPresenter
import br.com.gabrielmarcos.mercadopago.ui.MainActivity
import br.com.gabrielmarcos.mercadopago.utils.RadioAdapter
import kotlinx.android.synthetic.main.fragment_methods_payments.*
import android.util.Log
import br.com.gabrielmarcos.mercadopago.presenter.MainPresenter
import br.com.gabrielmarcos.mercadopago.utils.Constants
import br.com.gabrielmarcos.mercadopago.utils.CustomViews


/**
 * Created by Gabriel Marcos on 06/11/2018.
 */

class PaymentMethodsFragment: Fragment(), PaymentMethodsViewContract, RadioAdapter.RadioAdapterListener {

    private var bannerSelected = ""

    private lateinit var paymentMethodsPresenter: PaymentMethodsPresenter
    private lateinit var mainPresenter: MainPresenter
    private lateinit var paymentMethodsList: ArrayList<PaymentsModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_methods_payments, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainPresenter = MainPresenter(activity as MainActivity)

        paymentMethodsPresenter = PaymentMethodsPresenter(this@PaymentMethodsFragment,context!!)

        paymentMethodsPresenter.getPaymentsMethods()

        setupView()
    }

    override fun onDestroy() {
        super.onDestroy()
        print("")
    }

    override fun setData(paymentMethods: ArrayList<PaymentsModel>) {
        paymentMethodsList = paymentMethods
        setupListPaymentsMethods()
    }

    override fun setDataError(strError: String) {
        Log.d(Constants.ERROR_REQUEST, strError)
    }

    override fun showProgress() {
        mainPresenter?.showProgress()
    }

    override fun hideProgress() {
        mainPresenter?.hideProgress()
    }

    override fun radioChange(index: Int) {
        bannerSelected = paymentMethodsList[index].id
        mainPresenter?.setBanner(bannerSelected)
    }

    override fun validateFields(): Boolean {
        return if (bannerSelected.isNotBlank() && amountValueEdt.text.toString().isNotBlank()) {
            mainPresenter.setAmount(amountValueEdt.text.toString().replace(",", ""))
            amountValueEdt.text.clear()
            true
        } else {
            false
        }
    }

    override fun showDialog(message: String) {
        mainPresenter?.showDialog(message)
    }

    private fun setupView() {
        amountValueEdt.addTextChangedListener(CustomViews.onTextChangedListener(amountValueEdt))
    }

    private fun setupListPaymentsMethods() {
        methodsPaymentList.adapter = PaymentMethodsAdapter(context!!, paymentMethodsList, this@PaymentMethodsFragment)
        methodsPaymentList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}