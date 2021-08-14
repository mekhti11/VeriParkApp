package tr.com.mekhti.veriparkapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tr.com.mekhti.veriparkapp.api.StocksApi
import tr.com.mekhti.veriparkapp.model.Stocks
import tr.com.mekhti.veriparkapp.services.StocksServices
import tr.com.mekhti.veriparkapp.utils.Constants
import tr.com.mekhti.veriparkapp.utils.DecEncAes

class StocksViewModel : ViewModel() {

    var api:StocksApi
    private val stocksServices: StocksServices = StocksServices()
    var stocks = MutableLiveData<Stocks>()

    init {
        api = stocksServices.getApiService()
    }

    fun getStocks(period : String) {
        val map : HashMap<String, String> = HashMap()
        map["period"] = DecEncAes.encrypt(period.toByteArray())
        api.getStocks(body = map).enqueue(object : Callback<Stocks>{
            override fun onResponse(call: Call<Stocks>, response: Response<Stocks>) {
                if (response.isSuccessful){
                    if (response.body()!!.status.isSuccess){
                        stocks.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<Stocks>, t: Throwable) {

            }
        })
    }
}