package br.com.gabrielmarcos.mercadopago.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.models.AmountToPayModel
import br.com.gabrielmarcos.mercadopago.ui.banksAccepted.BanksAcceptedFragment
import br.com.gabrielmarcos.mercadopago.ui.paymentsMethods.PaymentMethodsFragment
import br.com.gabrielmarcos.mercadopago.ui.recommendation.RecommendationFragment
import br.com.gabrielmarcos.mercadopago.utils.BaseContract
import br.com.gabrielmarcos.mercadopago.utils.OnCommunicateInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var amount = ""
        var banner = ""
        var bank =  ""
    }

    private var currentPage = 0
    private var fragments: ArrayList<Fragment> = arrayListOf(PaymentMethodsFragment(), BanksAcceptedFragment(), RecommendationFragment())
    private var tags = arrayListOf("Payments Methods", "Banks", "Recommendations")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(currentPage)
        setupButtons()
    }

    private fun setupButtons() {
        nextButton.setOnClickListener {
            if (currentPage < fragments.size-1) {
                currentPage++
                loadFragment(currentPage)
            } else {
                currentPage = 0
                loadFragment(currentPage)
            }
        }
    }

    private fun changeButtonText() {
        if (currentPage == fragments.size-1) {
            nextButton.text = getString(R.string.title_button_try_again)
        } else {
            nextButton.text = getString(R.string.title_button_next)
        }
    }

    private fun loadFragment(index: Int): Boolean {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (supportFragmentManager.findFragmentByTag(tags[index]) == null) {
            fragmentTransaction.add(R.id.basePayments, fragments[index])
        }

        if (currentPage != 0) {
            fragmentTransaction.detach(fragments[currentPage-1])
        }

        fragmentTransaction.show(fragments[index])
        fragmentTransaction.commit()
        changeButtonText()

        return true
    }

}
