package br.com.gabrielmarcos.mercadopago.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.ui.paymentsMethods.PaymentMethodsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentPage = 0
    private var fragments: ArrayList<Fragment> = arrayListOf(PaymentMethodsFragment())
    private var tags = arrayListOf("Payments Methods")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(currentPage)
        setupButtons()
    }

    private fun setupButtons() {
        nextButton.setOnClickListener {
            if (currentPage <= fragments.size) {
                loadFragment(currentPage++)
            } else {
                finish()
            }

        }
    }

    private fun loadFragment(index: Int): Boolean {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (supportFragmentManager.findFragmentByTag(tags[index]) == null) {
            fragmentTransaction.add(R.id.basePayments, fragments[index])
        }

        fragmentTransaction.show(fragments[index])
        fragmentTransaction.commit()

        return true
    }

}
