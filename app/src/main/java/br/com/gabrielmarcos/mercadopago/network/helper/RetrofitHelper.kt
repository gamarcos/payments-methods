package br.com.gabrielmarcos.mercadopago.network.helper

import android.content.Context
import br.com.gabrielmarcos.mercadopago.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Gabriel Marcos on 05/11/2018
 */
class RetrofitHelper {
    companion object {
        fun getRetrofit(context: Context): Retrofit {
            return Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
        }
    }
}