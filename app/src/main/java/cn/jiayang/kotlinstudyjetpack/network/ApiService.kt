package cn.jiayang.kotlinstudyjetpack.network

import cn.jiayang.kotlinstudyjetpack.KotlinApplication
import cn.jiayang.kotlinstudyjetpack.entity.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author ：张 奎
 * @date ：2020-04-29 15：43
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
interface ApiService {

    @GET("v2/place?token=${KotlinApplication.TOKEN}&lang=zh_CN")
    suspend fun searchPlaces(@Query("query") query: String): PlaceResponse
}