package tr.com.mekhti.veriparkapp.model


import com.google.gson.annotations.SerializedName

data class Stocks(
    @SerializedName("status")
    val status: Status,
    @SerializedName("stocks")
    val stocks: List<Stock>
)