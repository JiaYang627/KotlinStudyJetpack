package cn.jiayang.kotlinstudyjetpack.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.jiayang.kotlinstudyjetpack.base.BaseRvAdapter
import cn.jiayang.kotlinstudyjetpack.base.BaseViewHolder
import cn.jiayang.kotlinstudyjetpack.databinding.ItemPlaceBinding
import cn.jiayang.kotlinstudyjetpack.entity.Place
import cn.jiayang.kotlinstudyjetpack.network.ApiService
import cn.jiayang.kotlinstudyjetpack.network.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author ：张 奎
 * @date ：2020-04-29 10：57
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class MainAdapter(list: MutableList<Place>? = mutableListOf<Place>()) :
    BaseRvAdapter<Place>(list) {


    override fun setVariable(data: Place, position: Int, holder: BaseViewHolder) {

        (holder.mBinding as ItemPlaceBinding).let {
            it.placeName.text = data.name
            it.placeAddress.text = data.address
        }
    }

    override fun setViewHolder(parent: ViewGroup, viewType: Int): ItemPlaceBinding =
        ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)

}

class MainRepository{
    suspend fun getSearchPlace(mEditStr: String) = withContext(Dispatchers.IO){
        RetrofitManager.createService<ApiService>().searchPlaces(mEditStr)
    }


}