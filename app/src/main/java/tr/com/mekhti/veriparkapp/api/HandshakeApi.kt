package tr.com.mekhti.veriparkapp.api

import retrofit2.Call
import retrofit2.http.*
import tr.com.mekhti.veriparkapp.model.Handshake


interface HandshakeApi {

    @Headers("Content-Type: application/json")
    @POST("start")
    fun handshake(
        @Body queries : HashMap<String, String>
        ): Call<Handshake>

}