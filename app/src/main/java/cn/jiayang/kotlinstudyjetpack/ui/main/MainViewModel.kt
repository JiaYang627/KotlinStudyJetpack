package cn.jiayang.kotlinstudyjetpack.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.jiayang.kotlinstudyjetpack.base.safeLaunch
import cn.jiayang.kotlinstudyjetpack.entity.Place

/**
 * @author ：张 奎
 * @date ：2020-04-29 14：15
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class MainViewModel(private val repository: MainRepository) :ViewModel() {



    val mPlaces = MutableLiveData<List<Place>>()

    fun searchPlace(mEditStr: String) {


        viewModelScope.safeLaunch {
            block ={
                val searchPlace = repository.getSearchPlace(mEditStr)
                if (searchPlace.status == "ok") {
                    mPlaces.value = searchPlace.places
                }
            }
        }
    }


}