package tr.com.mekhti.veriparkapp.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import tr.com.mekhti.veriparkapp.utils.DecEncAes

class ActivityDetailBinding {
    companion object {
        @BindingAdapter("setSymbol")
        @JvmStatic
        fun setSymbol(textView: TextView, symbol: String) {
            textView.text = DecEncAes.decrypt(symbol.toByteArray())
        }

        @BindingAdapter("setDouble")
        @JvmStatic
        fun setDouble(textView: TextView, value: Double) {
            textView.text =  String.format("%.2f", value)
        }

        @BindingAdapter("setInt")
        @JvmStatic
        fun setInt(textView: TextView, value: Int) {
            textView.text =  value.toString()
        }
    }
}