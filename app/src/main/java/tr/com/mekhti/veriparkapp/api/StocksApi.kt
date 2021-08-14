package tr.com.mekhti.veriparkapp.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import tr.com.mekhti.veriparkapp.model.StockDetail
import tr.com.mekhti.veriparkapp.model.Stocks
import tr.com.mekhti.veriparkapp.utils.HandshakeData

interface StocksApi {

    @Headers("Content-Type: application/json")
    @POST("list")
    fun getStocks(
        @Header( "X-VP-Authorization") auth:String = HandshakeData.authorization,
        @Body body : HashMap<String, String>
    ): Call<Stocks>

    @Headers("Content-Type: application/json")
    @POST("detail")
    fun getStockDetail(
        @Header(value = "X-VP-Authorization:") auth:String = HandshakeData.authorization,
        @Body body : HashMap<String, String>
    ): Call<StockDetail>
}