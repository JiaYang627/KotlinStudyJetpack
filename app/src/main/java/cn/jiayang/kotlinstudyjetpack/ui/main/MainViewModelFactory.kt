package cn.jiayang.kotlinstudyjetpack.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author ：张 奎
 * @date ：2020-04-29 14：17
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class MainViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return MainViewModel(mainRepository) as T
    }
}