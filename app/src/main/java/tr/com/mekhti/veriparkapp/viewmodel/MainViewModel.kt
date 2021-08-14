package tr.com.mekhti.veriparkapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tr.com.mekhti.veriparkapp.services.HandshakeService
import tr.com.mekhti.veriparkapp.model.Handshake

class MainViewModel : ViewModel() {

    private val handshakeService = HandshakeService()
    val handshake = MutableLiveData<Handshake>()
    val error = MutableLiveData<String>()


    fun getHandshake(queries:HashMap<String,String>) {
        handshakeService.getApiService().handshake(queries).enqueue(object : Callback<Handshake>{
            override fun onResponse(call: Call<Handshake>, response: Response<Handshake>) {
                if (response.isSuccessful){
                    handshake.value = response.body()
                }else{
                    error.value = response.errorBody().toString()
                }
            }

            override fun onFailure(call: Call<Handshake>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}