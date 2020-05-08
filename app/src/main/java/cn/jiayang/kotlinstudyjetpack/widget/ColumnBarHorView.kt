package com.example.studykotlin

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import cn.jiayang.kotlinstudyjetpack.entity.ColumnBarData


/**
 * @author ：张 奎
 * @date ：2020-05-04 17：07
 * 邮箱   ：JiaYang627@163.com
 */
class ColumnBarHorView : View {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val mContext: Context = context
    private var mDataBean: ColumnBarData? = ColumnBarData()
    private var mLeftColorHaveValue: Boolean = false
    private var mRightColorHaveValue: Boolean = false


    //给定义的画笔进行加工
    //给定义的画笔进行加工
    private val mLinePaint: Paint = Paint()
    private val mGreenPaint: Paint = Paint()
    private val mTextPaint: Paint = Paint()
    private val mScale: Float = context.resources.displayMetrics.density
    private var mWidth: Float = 0.0f
    private var mHeight: Float = 0.0f

    private var mDataLength: Float = 0.0f
    private var mSpaceLength: Float = 0.0f
    private var mSpaceLengthMiddle: Float = 0.0f
    private var mLength: Float = 0.0f

    private val DATA_IN_SCREEN_NUMBER: Int = 8
    private val SPACE_MIDDLE_IN_SCREEN_NUMBER: Int = 4

    private var mDownX: Float = 0f

    //这个数组是Y轴默认高度的值
    private var mYTitle =
        arrayOf("75", "60", "45", "30", "15", "0")


    init {

        mLinePaint.setARGB(255, 223, 233, 231)
        mGreenPaint.setARGB(255, 0, 200, 149)
        mTextPaint.setARGB(255, 153, 153, 153)

        mGreenPaint.style = Paint.Style.FILL

        mTextPaint.isAntiAlias = true
        mGreenPaint.isAntiAlias = true
        mLinePaint.isAntiAlias = true

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = 0.7f * MeasureSpec.getSize(widthMeasureSpec)
        mHeight = 0.70f * MeasureSpec.getSize(heightMeasureSpec)
        mDataLength =
            mWidth * 0.55f / DATA_IN_SCREEN_NUMBER
        mSpaceLength =
            mWidth * 0.35f / DATA_IN_SCREEN_NUMBER
        mSpaceLengthMiddle = mWidth * 0.1f / SPACE_MIDDLE_IN_SCREEN_NUMBER
        mLength = 0f
    }


//    @SuppressLint("DrawAllocation")
//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//
//        val mAverHeight = mHeight / 5
//        mTextPaint.color = Color.BLACK
//        for (i in 5 downTo 0) {
//            // X轴线
//            if (i == 5) {
//                mLinePaint.setARGB(255, 131, 148, 144)
//            } else {
//                mLinePaint.setARGB(255, 223, 233, 231);
//            }
//
//            canvas?.drawLine(
//                25 * mScale,
//                10 * mScale + mAverHeight * i,
//                20 * mScale + mWidth / 7 *10,
//                10 * mScale + mAverHeight * i,
//                mLinePaint
//            )
//
//            mTextPaint.textAlign = Paint.Align.RIGHT
//            mTextPaint.textSize = 10 * mScale
//            canvas?.drawText(mYTitle[i], 20 * mScale, 12 * mScale + mAverHeight * i, mTextPaint)
//
//        }
//
//
//        val mAverWidth = mWidth / mDataBean!!.mDataList.size
//
//        Log.e("JY", "mAverWith ---> $mAverWidth")
//        mTextPaint.textSize = 12 * mScale
//        mTextPaint.textAlign = Paint.Align.CENTER
//
//
//        // 绘制柱形图 以及相关内容
//        for (index in mDataBean!!.mDataList.indices) {
//
//            var mLeftR = if (index % 2 == 0) {
//                if (mLeftColorHaveValue) {
//                    mGreenPaint.color =
//                        mContext.resources.getColor(mDataBean!!.getLeftPColumnColor())
//                } else {
//                    mGreenPaint.setARGB(255, 0, 200, 149)
//                }
//
////                (mAverWidth * 7 / 16f + index * mAverWidth)
//
//                mSpaceLength + index / 2 * (mDataLength * 2 + mSpaceLength * 2 + mSpaceLengthMiddle)
//
//            } else {
//                if (mRightColorHaveValue) {
//                    mGreenPaint.color =
//                        mContext.resources.getColor(mDataBean!!.getRightPColumnColor())
//                } else {
//                    mGreenPaint.setARGB(255, 255, 128, 1)
//                }
////                (1 / 16f * mAverWidth + index * mAverWidth)
//                mSpaceLength + mDataLength + mSpaceLengthMiddle + (index - 1) / 2 * (mDataLength * 2 + mSpaceLength * 2 + mSpaceLengthMiddle)
//            }
//
//            mLeftR += 25 * mScale + mLength
//
////            val mRightR = (mLeftR + mAverWidth / 2)
//            val mRightR = (mLeftR + mDataLength)
//            val mBottomR = ((10 * mScale) + mHeight)
//            val mTopR =
//                (mBottomR - (mHeight / (mDataBean!!.mYMaxNum - mDataBean!!.mYMinNum) * (mDataBean!!.mDataList[index] - mDataBean!!.mYMinNum)))
//
//
//            val rect = RectF(mLeftR, mTopR, mRightR, mBottomR)
//            canvas?.drawRect(rect, mGreenPaint)
//
//
//            if (mDataBean!!.mShowDataNum) {
//                canvas?.drawText(
//                    mDataBean!!.mDataList[index].toString(),
//                    mLeftR + (mRightR - mLeftR) / 2,
//                    mTopR - 5 * mScale,
//                    mTextPaint
//                )
//            }
//
//            // 底部菜名
//            if (index % 2 == 0 && mDataBean!!.mNameList.isNotEmpty()) {
//                canvas?.drawText(
//                    mDataBean!!.mNameList[index / 2],
//                    mRightR + mSpaceLengthMiddle / 2,
//                    mBottomR + 20 * mScale,
//                    mTextPaint
//                )
//            }
//
//
//        }
//
//
//        // 底部 标识
//
//        // 左侧
//        val mLeftL = 100 * mScale
//        val mLeftR = mLeftL + 10 * mScale
//        val mLeftB = (10 * mScale) + mHeight + 50 * mScale
//        val mLeftT = mLeftB - 10 * mScale
//
//        val rect = RectF(mLeftL, mLeftT, mLeftR, mLeftB)
//
//        if (mLeftColorHaveValue) {
//            mGreenPaint.color =
//                mContext.resources.getColor(mDataBean!!.getLeftPColumnColor())
//        } else {
//            mGreenPaint.setARGB(255, 0, 200, 149)
//        }
//        canvas?.drawRect(rect, mGreenPaint)
//
//        mTextPaint.textAlign = Paint.Align.LEFT
//        mTextPaint.textSize = 15 * mScale
//        canvas?.drawText(
//            mDataBean!!.getBottomLeftName(),
//            mLeftR + 5 * mScale,
//            mLeftT + 10 * mScale,
//            mTextPaint
//        )
//        // 右侧
//        val mRightL = 100 * mScale + mWidth / 2
//        val mRightR = mRightL + 10 * mScale
//        val mRightB = (10 * mScale) + mHeight + 50 * mScale
//        val mRightT = mRightB - 10 * mScale
//
//        val rectRight = RectF(mRightL, mRightT, mRightR, mRightB)
//
//        if (mRightColorHaveValue) {
//            mGreenPaint.color =
//                mContext.resources.getColor(mDataBean!!.getRightPColumnColor())
//        } else {
//            mGreenPaint.setARGB(255, 255, 128, 1)
//        }
//        canvas?.drawRect(rectRight, mGreenPaint)
//
//        mTextPaint.textAlign = Paint.Align.LEFT
//        mTextPaint.textSize = 15 * mScale
//        canvas?.drawText(
//            mDataBean!!.getBottomRightName(),
//            mRightR + 5 * mScale,
//            mRightT + 10 * mScale,
//            mTextPaint
//        )
//
//    }
//
//    private var mLeftPosition: Int = 0
//    private var mMaxLeftPosition: Int = 0
//
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//
//        if (mDataBean!!.mDataList.size <= 8) {
//            return true
//        }
//
//        var distance: Float = 0f
//
//        when (event?.action) {
//            MotionEvent.ACTION_DOWN -> {
//                mDownX = event.x
//            }
//            MotionEvent.ACTION_MOVE -> {
//                if (mDownX == 0f) {
//                    mDownX = event.x
//                }
//                // move right
//                if (mDownX - event.x < 0) {
//                    distance = abs(event.x - mDownX)
//                    if (mLeftPosition != 0) {
//                        mLength += distance
//                        if (mLength > mSpaceLength + mDataLength + mSpaceLengthMiddle) {
//                            mLength = 0f
//                            if (mLeftPosition > 0) {
//                                --mLeftPosition
//                            }
//                        }
//                    } else {
//                        mLength += distance
//                        if (mLength > 0) {
//                            mLength = 0f
//                        }
//                    }
//                    this.postInvalidate()
//                } else if (mDownX - event.x > 0) {
//                    // move left
//
//                    distance = abs(event.x - mDownX)
//                    if (mLeftPosition != mMaxLeftPosition) {
//                        mLength -= distance
//                        if (mLength < -mDataLength - mSpaceLength - mSpaceLengthMiddle) {
//                            mLength = 0f
//                            if (mLeftPosition < mMaxLeftPosition) {
//                                ++mLeftPosition
//                            }
//                        }
//                    } else {
//                        mLength -= distance
//                        var v = 0f
//                        when(mDataBean!!.mDataList.size % 3) {
//                             0 -> {
//                                v =
//                                    -mWidth * (mDataBean!!.mDataList.size / DATA_IN_SCREEN_NUMBER - 1)
//                            }
//                            1 -> {
//                                v =
//                                    -mWidth * (mDataBean!!.mDataList.size / DATA_IN_SCREEN_NUMBER - 1) - 100 * mScale
//                            }
//                            2 -> {
//                                v =
//                                    -mWidth * (mDataBean!!.mDataList.size / DATA_IN_SCREEN_NUMBER) + 100 * mScale
//                            }
//
//
//                        }
//
//
//
//                        if (mLength < v) {
//                            mLength = v
//                        }
//                    }
//                    this.postInvalidate()
//                }
//                mDownX = event.x
//            }
//            MotionEvent.ACTION_UP -> {
//                mDownX = 0f
//            }
//            else -> {
//
//            }
//        }
//        return true
//    }
//
//
//    fun setNewDataBean(mDataBean: ColumnBarData) {
//        this.mDataBean = mDataBean
//        setYNum(mDataBean.mYMaxNum, mDataBean.mYMinNum)
//        mLeftColorHaveValue = mDataBean.getLeftPColumnColor() != 0
//        mRightColorHaveValue = mDataBean.getRightPColumnColor() != 0
//
//        invalidate()
//    }
//
//    private fun setYNum(mNum: Int, mYMinNum: Int) {
//
//        var mAverNum = (mNum - mYMinNum) / 5
//        for (i in 5 downTo 0) {
//            mYTitle[i] = (mNum - mAverNum * i).toString()
//        }
//
//
//    }


}