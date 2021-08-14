package tr.com.mekhti.veriparkapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tr.com.mekhti.veriparkapp.api.StocksApi
import tr.com.mekhti.veriparkapp.utils.Constants

class StocksServices {

    private val api : StocksApi = Retrofit.Builder()
        .baseUrl(Constants.STOCKS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StocksApi::class.java)

    fun getApiService(): StocksApi {
        return api
    }
}