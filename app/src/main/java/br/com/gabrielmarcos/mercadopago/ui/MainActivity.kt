package br.com.gabrielmarcos.mercadopago.ui

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import br.com.gabrielmarcos.mercadopago.R
import br.com.gabrielmarcos.mercadopago.models.AmountToPayModel
import br.com.gabrielmarcos.mercadopago.presenter.MainPresenter
import br.com.gabrielmarcos.mercadopago.ui.banksAccepted.BanksAcceptedFragment
import br.com.gabrielmarcos.mercadopago.ui.paymentsMethods.PaymentMethodsFragment
import br.com.gabrielmarcos.mercadopago.ui.recommendation.RecommendationFragment
import br.com.gabrielmarcos.mercadopago.utils.BaseContract
import br.com.gabrielmarcos.mercadopago.utils.CustomViews
import br.com.gabrielmarcos.mercadopago.utils.OnCommunicateInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainViewContract {

    companion object {
        var amount = ""
        var banner = ""
        var bank =  ""
    }

    private var currentPage = 0
    private var fragmentsList: ArrayList<Fragment> = arrayListOf(PaymentMethodsFragment(), BanksAcceptedFragment(), RecommendationFragment())
    private var tagsList = arrayListOf("Payments Methods", "Banks", "Recommendations")

    private lateinit var dialog: Dialog
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(0)
        setupButtons()
    }

    override fun getBank(): String {
        return bank
    }

    override fun getBanner(): String {
        return banner
    }

    override fun getAmount(): String {
        return amount
    }

    override fun setBank(bankValue: String) {
        bank = bankValue
    }

    override fun setBanner(bannerValue: String) {
        banner = bannerValue
    }

    override fun setAmount(amountValue: String) {
        amount = amountValue
    }

    override fun showProgress() {
        progressIndicator.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressIndicator.visibility = View.GONE
    }

    override fun resetFragment() {
        loadFragment(0)
    }

    override fun showDialog(message: String) {
        dialog = CustomViews.alertDialog(this,
            "OPS!",
            message,
            "OK",
            View.OnClickListener {
                resetFragment()
                dialog.dismiss()
            })

        dialog.show()
    }

    private fun setupButtons() {
        nextButton.setOnClickListener {
            if ((fragmentsList[currentPage] as BaseContract).validateFields()) {
                when (currentPage) {
                    0 -> loadFragment(1)
                    1 -> loadFragment(2)
                    2 -> loadFragment(0)
                }
            } else {
                showSnackBar()
            }
        }
    }

    private fun changeButtonText(position: Int) {
        if (position == fragmentsList.size-1) {
            nextButton.text = getString(R.string.title_button_try_again)
        } else {
            nextButton.text = getString(R.string.title_button_next)
        }
    }

    private fun loadFragment(index: Int): Boolean {
        val fragment = fragmentsList[index]
        val tag = tagsList[index]

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            fragmentTransaction.add(R.id.basePayments, fragment, tag)
        }

        if (currentPage != 0) {
            fragmentTransaction.remove(fragmentsList[currentPage])
        } else {
            fragmentTransaction.remove(fragmentsList[fragmentsList.size-1])
        }

        fragmentTransaction.hide(fragmentsList[currentPage])
        fragmentTransaction.show(fragment)
        fragmentTransaction.commit()
        changeButtonText(index)

        currentPage = index

        return true
    }

    private fun showSnackBar() {
        Snackbar.make(findViewById(R.id.mainActvitiy), "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

}
