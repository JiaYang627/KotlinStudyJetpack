package cn.jiayang.kotlinstudyjetpack.base

import android.graphics.Color
import android.graphics.Matrix
import cn.jiayang.kotlinstudyjetpack.R
import cn.jiayang.kotlinstudyjetpack.entity.ColumnBarData
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlin.math.abs

/**
 * @author ：张 奎
 * @date ：2020-05-08 09：02
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
fun BarChart.setNewData(mDataBean: ColumnBarData) {

    description.isEnabled = false
    axisRight.isEnabled = false


    // scaling can now only be done on x- and y-axis separately
    setPinchZoom(false)
    setDrawBarShadow(false)
    setDrawGridBackground(false)

//    extraBottomOffset = 10f //图例间隔X轴
//    extraTopOffset = 10f
//
    // 图例，由于不能设置 两个图例之间的距离，不使用第三方的图例
    legend.isEnabled = false
//    legend.apply {
//
//        verticalAlignment =
//            Legend.LegendVerticalAlignment.BOTTOM
//        horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
//        orientation = Legend.LegendOrientation.VERTICAL
//
//        textSize = 12f
//        setDrawInside(false)//图例是在图表内绘制 还是在图标外绘制
//
//        xEntrySpace  = 6f
//        yEntrySpace = 6f
//
//    }


    val myMarkerView = MyMarkerView(this.context, R.layout.custom_marker_view)
    myMarkerView.chartView = this
    marker = myMarkerView


    xAxis.apply {
        granularity = 1f //  设置缩放轴得最小间隔
        setDrawAxisLine(false)
        setDrawGridLines(false)
        axisMinimum = 0f
        textColor = Color.BLACK //设置横坐标字体颜色
        axisLineColor = Color.parseColor("#dddddd") //设置横轴颜色
        setCenterAxisLabels(true)
        position = XAxis.XAxisPosition.BOTTOM

        if (mDataBean.mNameList.isNotEmpty()) {
            valueFormatter = MyXAxisValueFormatter(mDataBean.mNameList)
        }
    }

    axisLeft.apply {
        setLabelCount(5, true)
        setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        granularity = 15f
        axisMinimum = mDataBean.mYMinNum.toFloat()
        axisMaximum = mDataBean.mYMaxNum.toFloat()
        enableGridDashedLine(10f, 10f, 1f)
    }

    val mGroupSpace: Float = 0.5f
    val mBarSpace: Float = 0.1f
    val mBarWidth: Float = 0.15f


    val mValueOne: MutableList<BarEntry> = mutableListOf()
    val mValueTwo: MutableList<BarEntry> = mutableListOf()


    for (index in mDataBean.mDataOneList.indices) {

        mValueOne.add(BarEntry(index.toFloat(), mDataBean.mDataOneList[index].toFloat()))
        mValueTwo.add(BarEntry(index.toFloat(), mDataBean.mDataTwoList[index].toFloat()))
    }


    val mBarSetOne: BarDataSet?
    val mBarSetTwo: BarDataSet?

    if (data != null && childCount > 0) {

        mBarSetOne = data.getDataSetByIndex(0) as BarDataSet?
        mBarSetTwo = data.getDataSetByIndex(1) as BarDataSet?

        mBarSetOne!!.values = mValueOne
        mBarSetTwo!!.values = mValueTwo

        data.notifyDataChanged()
        notifyDataSetChanged()
    } else {

        mBarSetOne = BarDataSet(mValueOne, mDataBean.getBottomLeftName())
        mBarSetTwo = BarDataSet(mValueTwo, mDataBean.getBottomRightName())

        if (mDataBean.getLeftPColumnColor() != 0) {
            mBarSetOne.color = context.resources.getColor(mDataBean.getLeftPColumnColor())
        } else {
            mBarSetOne.color = Color.rgb(239, 134, 51)
        }

        if (mDataBean.getRightPColumnColor() != 0) {
            mBarSetTwo.color = context.resources.getColor(mDataBean.getRightPColumnColor())
        } else {
            mBarSetTwo.color = Color.rgb(59, 145, 248)
        }

        data = BarData(mBarSetOne, mBarSetTwo)
    }

    barData.barWidth = mBarWidth
    xAxis.axisMinimum = 0f

    xAxis.axisMaximum =
        0f + barData.getGroupWidth(mGroupSpace, mBarSpace) * mDataBean.mDataOneList.size

    groupBars(0f, mGroupSpace, mBarSpace)

    invalidate()

    var matix = Matrix()
    matix.postScale(2.0f, 1f)
    viewPortHandler.refresh(matix, this, false)
    animateX(500)

}

class MyXAxisValueFormatter(private val mNameList: MutableList<String>) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return mNameList[abs(value.toInt()) % mNameList.size]
    }

}