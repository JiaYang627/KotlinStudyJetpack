package cn.jiayang.kotlinstudyjetpack.network

import cn.jiayang.kotlinstudyjetpack.utils.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author ：张 奎
 * @date ：2020-04-29 15：36
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
object RetrofitManager {


    private const val BASE_URL ="https://api.caiyunapp.com/"

    private val retrofit =Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(genericOkClient())
        .build()



    private fun genericOkClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(
            object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {

                    // 如果是 json 格式内容则打印 json
                    if ((message.startsWith("{") && message.endsWith("}")) ||
                        (message.startsWith("[") && message.endsWith("]"))
                    )
                        LogUtils.json(message)
                    else
                        LogUtils.verbose(message)
                }
            })

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(5_000L, TimeUnit.MILLISECONDS)
            .readTimeout(10_000, TimeUnit.MILLISECONDS)
            .writeTimeout(30_000, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    fun <T> create(service: Class<T>) :T = retrofit.create(service)

    inline fun <reified T> createService() :T = create(T::class.java)
}