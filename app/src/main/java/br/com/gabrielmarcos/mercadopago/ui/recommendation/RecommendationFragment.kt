package br.com.gabrielmarcos.mercadopago.ui.recommendation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.models.RecommendedMessageModel
import br.com.gabrielmarcos.mercadopago.presenter.RecommendationPresenter
import br.com.gabrielmarcos.mercadopago.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_recomendation.*

/**
 * Created by Gabriel Marcos on 07/11/2018.
 */

class RecommendationFragment: Fragment(), RecommendationContractView {

    private lateinit var recommendationPresenter: RecommendationPresenter
    private lateinit var recommendationsList: ArrayList<RecommendedMessageModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recomendation, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recommendationPresenter = RecommendationPresenter(this@RecommendationFragment, context!!)

        recommendationPresenter.getPaymentsMethods(MainActivity.amount, MainActivity.banner, MainActivity.bank)

    }

    override fun setData(recommendations: ArrayList<RecommendedMessageModel>) {
        recommendationsList = recommendations
        if (recommendationsList.isEmpty()) {
            setDataError("Não foi possivel encontrar nenhum banco no momento, tente mais tarde")
        } else {
            setupAdapter()
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

    override fun validateFields(): Boolean {
        return true
    }

    private fun setupAdapter() {
        recommendationsRecyclerView.adapter = RecommendationAdapter(context!!, recommendationsList)
        recommendationsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}