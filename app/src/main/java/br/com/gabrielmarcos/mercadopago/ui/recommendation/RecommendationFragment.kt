package br.com.gabrielmarcos.mercadopago.ui.recommendation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.models.RecommendedMessageModel
import br.com.gabrielmarcos.mercadopago.presenter.MainPresenter
import br.com.gabrielmarcos.mercadopago.presenter.RecommendationPresenter
import br.com.gabrielmarcos.mercadopago.ui.MainActivity
import br.com.gabrielmarcos.mercadopago.utils.Constants
import kotlinx.android.synthetic.main.fragment_recomendation.*

/**
 * Created by Gabriel Marcos on 07/11/2018.
 */

class RecommendationFragment: Fragment(), RecommendationContractView {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var recommendationPresenter: RecommendationPresenter
    private lateinit var recommendationsList: ArrayList<RecommendedMessageModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recomendation, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainPresenter = MainPresenter(activity as MainActivity)

        recommendationPresenter = RecommendationPresenter(this@RecommendationFragment, context!!)

        recommendationPresenter.getPaymentsMethods(
            mainPresenter.getAmount(),
            mainPresenter.getBanner(),
            mainPresenter.getBank())
    }

    override fun setData(recommendations: ArrayList<RecommendedMessageModel>) {
        recommendationsList = recommendations
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

    override fun validateFields(): Boolean {
        return true
    }

    override fun showDialog(message: String) {
        mainPresenter.showDialog(message)
    }

    private fun setupAdapter() {
        recommendationsRecyclerView.adapter = RecommendationAdapter(context!!, recommendationsList)
        recommendationsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}