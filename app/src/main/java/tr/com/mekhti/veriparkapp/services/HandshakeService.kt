package tr.com.mekhti.veriparkapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tr.com.mekhti.veriparkapp.api.HandshakeApi
import tr.com.mekhti.veriparkapp.utils.Constants

class HandshakeService {

    private val api : HandshakeApi = Retrofit.Builder()
        .baseUrl(Constants.HANDSHAKEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HandshakeApi::class.java)

    fun getApiService(): HandshakeApi {
        return api
    }
}