package tr.com.mekhti.veriparkapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_detail.*
import tr.com.mekhti.veriparkapp.R
import tr.com.mekhti.veriparkapp.databinding.ActivityDetailBinding
import tr.com.mekhti.veriparkapp.model.GraphicData
import tr.com.mekhti.veriparkapp.view.Custom.CustomMarker
import tr.com.mekhti.veriparkapp.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val id:Int = intent.getIntExtra("id",0)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getStockDetail(id)
        viewModel.detail.observe(this,{ detail ->
            val binding: ActivityDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_detail)
            binding.detail = detail
            setUpLineChart(detail.graphicData)
        })


    }

    private fun setUpLineChart(graphicData: List<GraphicData>) {
        val entries = ArrayList<Entry>()

        graphicData.forEach {
            entries.add(Entry(it.day.toFloat(),it.value.toFloat()))
            Log.d("SIZE", "setUpLineChart: "+it.value)
        }

        val vl = LineDataSet(entries, "My Type")



        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.gray
        vl.fillAlpha = R.color.red

        lineChart.xAxis.labelRotationAngle = 0f

        lineChart.data = LineData(vl)

        lineChart.axisRight.isEnabled = false
        lineChart.xAxis.axisMaximum = 5+0.1f

        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)

        lineChart.description.text = "Days"
        lineChart.setNoDataText("No data yet!")

        lineChart.animateX(1800, Easing.EaseInExpo)

        val markerView = CustomMarker(this@DetailActivity, R.layout.marker_view)
        lineChart.marker = markerView
    }
}