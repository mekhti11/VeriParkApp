package tr.com.mekhti.veriparkapp.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import tr.com.mekhti.veriparkapp.utils.DecEncAes

class StockRawBinding {

    companion object {
        @BindingAdapter("decodeSymbol")
        @JvmStatic
        fun decodeSymbol(textView: TextView, symbol: String) {
            textView.text = DecEncAes.decrypt(symbol.toByteArray())
        }

        @BindingAdapter("setDoubleValue")
        @JvmStatic
        fun setDoubleValue(textView: TextView, value: Double) {
            textView.text = value.toString()
        }
    }
}