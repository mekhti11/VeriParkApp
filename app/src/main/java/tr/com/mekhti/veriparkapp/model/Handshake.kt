package tr.com.mekhti.veriparkapp.model


import com.google.gson.annotations.SerializedName

data class Handshake(
    @SerializedName("aesIV")
    val aesIV: String,
    @SerializedName("aesKey")
    val aesKey: String,
    @SerializedName("authorization")
    val authorization: String,
    @SerializedName("lifeTime")
    val lifeTime: String,
    @SerializedName("status")
    val status: Status
)