package br.com.gabrielmarcos.mercadopago.ui.banksAccepted

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.models.BankModel
import br.com.gabrielmarcos.mercadopago.presenter.BanksAcceptedPresenter
import br.com.gabrielmarcos.mercadopago.ui.MainActivity
import br.com.gabrielmarcos.mercadopago.utils.RadioAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_methods_payment.*
import kotlinx.android.synthetic.main.fragment_banks.*

/**
 * Created by Gabriel Marcos on 07/11/2018
 */
class BanksAcceptedFragment: Fragment(), BanksAcceptedViewContract , RadioAdapter.RadioAdapterListener {

    private var bankSelected = ""

    private lateinit var banksAcceptedPresenter: BanksAcceptedPresenter
    private lateinit var banckList: ArrayList<BankModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banks, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        banksAcceptedPresenter = BanksAcceptedPresenter(this, context!!)

        banksAcceptedPresenter.getBankList(MainActivity.banner)
    }

    override fun setData(paymentMethods: ArrayList<BankModel>) {
        banckList = paymentMethods
        if (banckList.isEmpty()) {
            setDataError("Não foi possivel encontrar nenhum banco no momento, tente mais tarde")
        } else {
            setupAdapter()
        }
    }

    override fun setDataError(strError: String) {}

    override fun showProgress() {
        (activity as MainActivity).showProgress()
    }

    override fun hideProgress() {
        (activity as MainActivity).hideProgress()
    }

    override fun radioChange(index: Int) {
        bankSelected = banckList[index].id
        MainActivity.bank = bankSelected
    }

    override fun validateFields(): Boolean {
        return bankSelected.isNotBlank()
    }

    private fun setupAdapter() {
        banksList.adapter = BanksAcceptedAdapter(context!!, banckList, this@BanksAcceptedFragment)
        banksList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}