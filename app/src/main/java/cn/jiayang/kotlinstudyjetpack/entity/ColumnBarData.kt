package cn.jiayang.kotlinstudyjetpack.entity

/**
 * @author ：张 奎
 * @date ：2020-05-06 09：21
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class ColumnBarData {

    /**
     * 数据集合
     */
    var mDataOneList: MutableList<Int> = mutableListOf()
    var mDataTwoList: MutableList<Int> = mutableListOf()

    /**
     * 菜名 集合
     */
    var mNameList: MutableList<String> = mutableListOf()


    private var mLeftPColumnColor: Int = 0
    private var mRightPColumnColor: Int = 0
    private var mBottomLeftName: String = "预约份数"
    private var mBottomRightName: String = "消费份数"

    /**
     * Y轴最高值 大小，
     */
    var mYMaxNum: Int = 60
        get() {
            val coerceAtLeast = (mDataOneList.max()?:0).coerceAtLeast(mDataTwoList.max()?:0)
            return if (field < coerceAtLeast) {
                coerceAtLeast + 20
            } else field
        }
    /**
     * Y轴最小值 大小，
     */
    var mYMinNum: Int = 0
        get() {
            val coerceAtMost = (mDataOneList.min()?:10).coerceAtMost(mDataTwoList.min()?:10)

            return if (field > coerceAtMost) {
                coerceAtMost - 10
            } else field
        }
    /**
     * 柱形图 顶部是否显示当前数据 默认不显示
     */
    var mShowDataNum: Boolean = false


    /**
     * 设置 柱形图 颜色
     */
    fun setPColumnColorsId(mLeftColorID: Int, mRightColorID: Int) {
        mLeftPColumnColor = mLeftColorID
        mRightPColumnColor = mRightColorID
    }

    /**
     * 设置 底部 表示名字
     * 默认 mLeftName: 预约份数   mRightName:消费份数
     */
    fun setBottomName(mLeftName: String, mRightName: String) {
        mBottomLeftName = mLeftName
        mBottomRightName = mRightName

    }

    /**
     * 设置每组柱形图数据
     */
    fun setData(vararg mNum: Int) {
        for (i in mNum.indices) {
            if (i % 2 == 0) {
                mDataOneList.add(mNum[i])
            } else {
                mDataTwoList.add(mNum[1])
            }
        }
    }

    /**
     * 设置菜名
     */
    fun setName(vararg mName: String) {
        for (i in mName) {
            mNameList.add(i)
        }
    }

    fun getLeftPColumnColor() = mLeftPColumnColor
    fun getRightPColumnColor() = mRightPColumnColor

    fun getBottomLeftName() = mBottomLeftName
    fun getBottomRightName() = mBottomRightName

}