package br.com.gabrielmarcos.mercadopago.utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import br.com.gabrielmarcos.mercadopago.R
import android.widget.RadioButton
import android.widget.TextView


/**
 * Created by Gabriel Marcos on 06/11/2018.
 */

abstract class RadioAdapter<T>(private val mContext: Context, var mItems: ArrayList<T>): RecyclerView.Adapter<RadioAdapter<T>.ViewHolder>() {

    var mSelectedItem = -1

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.mRadio.isChecked = i == mSelectedItem
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.adapter_methods_payment, viewGroup, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {

        var mRadio: RadioButton = inflate.findViewById<View>(R.id.paymentMethodsRadio) as RadioButton
        var mText: TextView = inflate.findViewById<View>(R.id.paymentMethodsTitle) as TextView
        var mImage: ImageView = inflate.findViewById<View>(R.id.paymentImage) as ImageView

        init {
            val clickListener = View.OnClickListener {
                mSelectedItem = adapterPosition
                notifyItemRangeChanged(0, mItems.size)
            }
            itemView.setOnClickListener(clickListener)
            mRadio.setOnClickListener(clickListener)
        }
    }
}