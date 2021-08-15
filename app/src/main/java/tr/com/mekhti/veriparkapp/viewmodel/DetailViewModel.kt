package tr.com.mekhti.veriparkapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tr.com.mekhti.veriparkapp.api.StocksApi
import tr.com.mekhti.veriparkapp.model.StockDetail
import tr.com.mekhti.veriparkapp.services.StocksServices
import tr.com.mekhti.veriparkapp.utils.DecEncAes

class DetailViewModel : ViewModel(){
    var api: StocksApi
    private val stocksServices: StocksServices = StocksServices()
    var detail = MutableLiveData<StockDetail>()

    init {
        api = stocksServices.getApiService()
    }

    fun getStockDetail(id:Int){
        val map : HashMap<String, String> = HashMap()
        map["id"] = DecEncAes.encrypt(id.toString().toByteArray())
        api.getStockDetail(body = map).enqueue(object : Callback<StockDetail>{
            override fun onResponse(call: Call<StockDetail>, response: Response<StockDetail>) {
                if (response.isSuccessful){
                    if (response.body()!!.status.isSuccess){
                        detail.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<StockDetail>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}