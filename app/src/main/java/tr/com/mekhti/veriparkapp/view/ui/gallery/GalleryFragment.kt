package tr.com.mekhti.veriparkapp.view.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.view.*
import tr.com.mekhti.veriparkapp.R
import tr.com.mekhti.veriparkapp.adapters.StocksAdapter
import tr.com.mekhti.veriparkapp.model.Stocks
import tr.com.mekhti.veriparkapp.utils.Constants
import tr.com.mekhti.veriparkapp.utils.DecEncAes
import tr.com.mekhti.veriparkapp.view.DetailActivity
import tr.com.mekhti.veriparkapp.view.MainActivity
import tr.com.mekhti.veriparkapp.viewmodel.StocksViewModel

class GalleryFragment : Fragment() {

    lateinit var viewModel : StocksViewModel
    lateinit var adapter : StocksAdapter
    private lateinit var mView: View
    private lateinit var mStocks : Stocks

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =  ViewModelProvider(this).get(StocksViewModel::class.java)
        viewModel.getStocks(Constants.INCREASING)


        mView = inflater.inflate(R.layout.fragment_home, container, false)
        setUpRV()
        observeStocksList()
        textWatcher()
        return mView
    }

    private fun textWatcher() {
        mView.editTextFilter.doOnTextChanged { text, _, _, _ ->
            if (mStocks.stocks.isNotEmpty()){
                val filteredStocks = mStocks.stocks.filter { DecEncAes.decrypt(it.symbol.toByteArray()).contains(text.toString().uppercase()) }
                adapter.setData(filteredStocks)
            }
        }
    }

    private fun setUpRV() {
        adapter = StocksAdapter(onSelect = { stock ->
            activity?.let{
                val intent = Intent (it, DetailActivity::class.java)
                intent.putExtra("id",stock?.id)
                it.startActivity(intent)
            }
        })
        mView.rv.adapter = adapter
        mView.rv.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun observeStocksList() {
        viewModel.stocks.observe(viewLifecycleOwner,{ stocks ->
            adapter.setData(stocks.stocks)
            mStocks = stocks
        })
    }

}