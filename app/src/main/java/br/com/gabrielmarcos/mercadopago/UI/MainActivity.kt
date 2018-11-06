package br.com.gabrielmarcos.mercadopago.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.network.PaymentMethodsService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var paymentService: PaymentMethodsService? =  null

        paymentService = PaymentMethodsService(this)

        paymentService.getPaymentsMethods({
            print(it[0].id)
        },{})


    }
}
