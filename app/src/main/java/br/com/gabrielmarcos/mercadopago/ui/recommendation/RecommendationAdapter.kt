package br.com.gabrielmarcos.mercadopago.ui.recommendation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.models.RecommendedMessageModel
import kotlinx.android.synthetic.main.adapter_recommendations.view.*

/**
 * Created by Gabriel Marcos on 07/11/2018.
 */

class RecommendationAdapter(private val context: Context,
                            private val recommendations: ArrayList<RecommendedMessageModel>): RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_recommendations, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recommendations.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(recommendations[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(recommendation: RecommendedMessageModel) {
            itemView.amountIndicate.text = recommendation.recommendedMessage
        }
    }
}