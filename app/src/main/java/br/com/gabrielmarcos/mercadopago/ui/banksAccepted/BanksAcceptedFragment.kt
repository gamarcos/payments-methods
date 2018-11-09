package br.com.gabrielmarcos.mercadopago.ui.banksAccepted

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.models.BankModel
import br.com.gabrielmarcos.mercadopago.presenter.BanksAcceptedPresenter
import br.com.gabrielmarcos.mercadopago.presenter.MainPresenter
import br.com.gabrielmarcos.mercadopago.ui.MainActivity
import br.com.gabrielmarcos.mercadopago.utils.Constants
import br.com.gabrielmarcos.mercadopago.utils.RadioAdapter
import kotlinx.android.synthetic.main.fragment_banks.*

/**
 * Created by Gabriel Marcos on 07/11/2018
 */
class BanksAcceptedFragment: Fragment(), BanksAcceptedViewContract , RadioAdapter.RadioAdapterListener {

    private var bankSelected = ""

    private lateinit var mainPresenter: MainPresenter
    private lateinit var banksAcceptedPresenter: BanksAcceptedPresenter
    private lateinit var banckList: ArrayList<BankModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banks, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainPresenter = MainPresenter(activity as MainActivity)

        banksAcceptedPresenter = BanksAcceptedPresenter(this, context!!)

        banksAcceptedPresenter.getBankList(mainPresenter.getBanner())
    }

    override fun setData(paymentMethods: ArrayList<BankModel>) {
        banckList = paymentMethods
        setupAdapter()
    }

    override fun setDataError(strError: String) {
        Log.d(Constants.ERROR_REQUEST, strError)
    }

    override fun showProgress() {
        mainPresenter.showProgress()
    }

    override fun hideProgress() {
        mainPresenter.hideProgress()
    }

    override fun radioChange(index: Int) {
        bankSelected = banckList[index].id
        mainPresenter.setBank(bankSelected)
    }

    override fun validateFields(): Boolean {
        return bankSelected.isNotBlank()
    }

    override fun showDialog(message: String) {
        mainPresenter.showDialog(message)
    }

    private fun setupAdapter() {
        banksList.adapter = BanksAcceptedAdapter(context!!, banckList, this@BanksAcceptedFragment)
        banksList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}