package cn.jiayang.kotlinstudyjetpack.entity

import com.google.gson.annotations.SerializedName

/**
 * @author ：张 奎
 * @date ：2020-04-29 14：25
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
data class Place(
    val name: String,
    val location: Location,
    @SerializedName("formatted_address")
    val address: String
)

data class Location(
    val lng: String,
    val lat: String
)


data class PlaceResponse(
    val status: String,
    val places: MutableList<Place>
)