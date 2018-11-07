package br.com.gabrielmarcos.mercadopago.ui.banksAccepted

import android.content.Context
import br.com.gabrielmarcos.mercadopago.models.BankModel
import br.com.gabrielmarcos.mercadopago.utils.PicassoServiceHelper
import br.com.gabrielmarcos.mercadopago.utils.RadioAdapter

/**
 * Created by Gabriel Marcos on 07/11/2018
 */
class BanksAcceptedAdapter(context: Context, bankModel: ArrayList<BankModel>, listener: RadioAdapterListener):
    RadioAdapter<BankModel>(context, bankModel, listener) {

    private var picassoServiceHelper = PicassoServiceHelper(context)

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        super.onBindViewHolder(viewHolder, i)
        viewHolder.mText.text = mItems[i].name
        picassoServiceHelper.loadImage(mItems[i].thumbnail, viewHolder.mImage)
    }
}