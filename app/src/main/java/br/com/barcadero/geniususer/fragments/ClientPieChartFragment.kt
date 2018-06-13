package br.com.barcadero.geniususer.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.barcadero.geniususer.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.fragment_client_pie_chart.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class ClientPieChartFragment : BaseFragment(), OnChartValueSelectedListener {
    override fun onNothingSelected() {
        Log.i("PieChart", "nothing selected")
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e == null) return
        Log.i("VAL SELECTED",
                "Value: " + e.y + ", index: " + h?.getX()
                + ", DataSet index: " + h?.getDataSetIndex())
    }


    var mParties = arrayOf("Marcenario", "Ar Condicionado", "Eletricista", "Diarista", "Alvenaria",
            "Pintura", "Mecânica", "Party H", "Party I", "Party J", "Party K", "Party L",
            "Party M", "Party N", "Party O", "Party P", "Party Q", "Party R", "Party S",
            "Party T", "Party U", "Party V", "Party W", "Party X", "Party Y", "Party Z")

    //private var mChart: PieChart? = null
    //private var mSeekBarX: SeekBar? = null
    //private var mSeekBarY:SeekBar? = null
    //private var tvX: TextView? = null
    //private var tvY:TextView? = null
    var mTfRegular: Typeface?=null
    var mTfLight: Typeface?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setTitleBarForClient(R.string.title_activity_client_financial)
        return inflater.inflate(R.layout.fragment_client_pie_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTfRegular = Typeface.createFromAsset(activity?.assets, "OpenSans-Regular.ttf")
        mTfLight = Typeface.createFromAsset(activity?.assets, "OpenSans-Light.ttf")
        //tvX = findViewById(R.id.tvXMax)
        //tvY = findViewById(R.id.tvYMax)

        //mSeekBarX = findViewById(R.id.seekBarX)
        //mSeekBarY = findViewById(R.id.seekBar2)
       // seekBarX.setProgress(4)
        //seekBarY.setProgress(10)

        //mChart = findViewById(R.id.chart1)
        with(chart1) {
            setUsePercentValues(true)
            getDescription().setEnabled(false)
            setExtraOffsets(5F, 10F, 5F, 5F)
            setDragDecelerationFrictionCoef(0.95f)
            setCenterTextTypeface(mTfLight)
            setCenterText(generateCenterSpannableText())
            setDrawHoleEnabled(true)
            setHoleColor(Color.WHITE)
            setTransparentCircleColor(Color.WHITE)
            setTransparentCircleAlpha(110)
            setHoleRadius(58f)
            setTransparentCircleRadius(61f)
            setDrawCenterText(true)
            setRotationAngle(0F)
            // enable rotation of the chart by touch
            setRotationEnabled(true)
            setHighlightPerTapEnabled(true)
            // mChart.setUnit(" €");
            // mChart.setDrawUnitsInChart(true);
            // add a selection listener
            setOnChartValueSelectedListener(this@ClientPieChartFragment)
        }
        setData(7, 100F)

        chart1.animateY(1400, Easing.getEasingFunctionFromOption(Easing.EasingOption.EaseInOutQuad))
        // mChart.spin(2000, 0, 360);

        //seekBarX.setOnSeekBarChangeListener(this)
        //seekBarY.setOnSeekBarChangeListener(this)

        val l = chart1.getLegend()
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP)
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
        l.setOrientation(Legend.LegendOrientation.VERTICAL)
        l.setDrawInside(false)
        l.setXEntrySpace(7f)
        l.setYEntrySpace(0f)
        l.setYOffset(0f)

        // entry label styling
        chart1.setEntryLabelColor(Color.WHITE)
        chart1.setEntryLabelTypeface(mTfRegular)
        chart1.setEntryLabelTextSize(12f)
    }

    private fun generateCenterSpannableText(): SpannableString {

        val s = SpannableString("Genius\ndeveloped by barcadero")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
        s.setSpan(RelativeSizeSpan(.8f), 14, s.length - 15, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
        return s
    }

    private fun setData(count: Int, range: Float) {

        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(PieEntry((Math.random() * range + range / 5).toFloat(),
                    mParties[i % mParties.size],
                    resources.getDrawable(R.drawable.star_rate_24)))
        }

        val dataSet = PieDataSet(entries, "Election Results")

        dataSet.setDrawIcons(false)

        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors

        val colors = ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        data.setValueTypeface(mTfLight)
        chart1.setData(data)

        // undo all highlights
        chart1.highlightValues(null)

        chart1.invalidate()
    }
}// Required empty public constructor
