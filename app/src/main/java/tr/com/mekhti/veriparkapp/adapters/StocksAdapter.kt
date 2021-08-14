package tr.com.mekhti.veriparkapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tr.com.mekhti.veriparkapp.databinding.StockRawLayoutBinding
import tr.com.mekhti.veriparkapp.model.Stock
import tr.com.mekhti.veriparkapp.model.Stocks
import tr.com.mekhti.veriparkapp.utils.StocksDiffUtil

class StocksAdapter
    (private val onSelect: (Stock?) -> Unit)
    : RecyclerView.Adapter<StocksAdapter.StocksViewHolder>() {

    private var stocks = emptyList<Stock>()

    class StocksViewHolder(private val binding: StockRawLayoutBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(stock : Stock,onSelect: (Stock?) -> Unit) {
            binding.stock = stock

            binding.root.setOnClickListener {
                onSelect(stock)
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent : ViewGroup) : StocksViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = StockRawLayoutBinding.inflate(layoutInflater,parent,false)
                return StocksViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksViewHolder {
        return StocksViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: StocksViewHolder, position: Int) {
        val currentStock = stocks[position]
        holder.bind(currentStock,onSelect)
    }

    override fun getItemCount(): Int = stocks.size

    fun setData(data:List<Stock>){
        val recipesDiffUtil = StocksDiffUtil(stocks,data)
        val diffutilresult = DiffUtil.calculateDiff(recipesDiffUtil)
        stocks = data
        diffutilresult.dispatchUpdatesTo(this)
    }
}