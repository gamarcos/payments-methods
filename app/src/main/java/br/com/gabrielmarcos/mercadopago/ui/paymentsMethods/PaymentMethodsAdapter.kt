package br.com.gabrielmarcos.mercadopago.ui.paymentsMethods

import android.content.Context
import br.com.gabrielmarcos.mercadopago.models.PaymentsModel
import br.com.gabrielmarcos.mercadopago.utils.PicassoServiceHelper
import br.com.gabrielmarcos.mercadopago.utils.RadioAdapter

/**
 * Created by Gabriel Marcos on 05/11/2018
 */
class PaymentMethodsAdapter(context: Context,
                            paymentsMethods: ArrayList<PaymentsModel>): RadioAdapter<PaymentsModel>(context, paymentsMethods) {

    private var picassoServiceHelper = PicassoServiceHelper(context)

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        super.onBindViewHolder(viewHolder, i)
        viewHolder.mText.text = mItems[i].name
        picassoServiceHelper.loadImage(mItems[i].thumbnail, viewHolder.mImage)
    }
}