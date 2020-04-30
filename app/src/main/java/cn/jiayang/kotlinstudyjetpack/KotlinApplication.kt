package cn.jiayang.kotlinstudyjetpack

import android.app.Application
import android.content.Context

/**
 * @author ：张 奎
 * @date ：2020-04-28 14：13
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class KotlinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        mApplication = applicationContext
    }
    companion object{

        const val TOKEN = "BNBRvBveaD2VfHVI" // 填入你申请到的令牌值  使用郭霖项目中的 TOKEN值
        lateinit var mApplication: Context
    }
}