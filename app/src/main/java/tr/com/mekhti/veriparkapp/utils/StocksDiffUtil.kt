package tr.com.mekhti.veriparkapp.utils

import androidx.recyclerview.widget.DiffUtil
import tr.com.mekhti.veriparkapp.model.Stock

class StocksDiffUtil (
    private val oldStocks: List<Stock>,
    private val newStocks: List<Stock>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldStocks.size
    }

    override fun getNewListSize(): Int {
        return newStocks.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldStocks[oldItemPosition] == newStocks[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldStocks[oldItemPosition] == newStocks[newItemPosition]
    }

}