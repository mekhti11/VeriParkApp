package tr.com.mekhti.veriparkapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import tr.com.mekhti.veriparkapp.R
import tr.com.mekhti.veriparkapp.utils.HandshakeData
import tr.com.mekhti.veriparkapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        btn_imkb.setOnClickListener { _ -> imkbOnClick() }

        callHandshake()
        observeHandshakeData()
    }

    @SuppressLint("HardwareIds")
    private fun callHandshake() {
        val query : HashMap<String, String> = hashMapOf()
        val id: String = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        query["deviceId"] = id
        query["systemVersion"] = Build.VERSION.SDK_INT.toString()
        query["platformName"] = "Android"
        query["deviceModel"] = Build.MODEL
        query["manifacturer"] = Build.MANUFACTURER

        viewModel.getHandshake(query)
    }

    private fun observeHandshakeData() {
        viewModel.handshake.observe(this,{ handshake ->
            HandshakeData.apply {
                aesIV = handshake.aesIV
                aesKey = handshake.aesKey
                authorization = handshake.authorization
            }
            btn_imkb.isEnabled = true
            Log.d("Handshake", "observeHandshakeData: ${handshake.authorization}")
        })

        viewModel.error.observe(this,{ error ->
            Log.d("Error", "observeHandshakeData: $error")
        })

    }

    private fun imkbOnClick() {
        startActivity(Intent(this,StocksActivity::class.java))
    }
}