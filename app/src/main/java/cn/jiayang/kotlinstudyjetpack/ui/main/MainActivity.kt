package cn.jiayang.kotlinstudyjetpack.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jiayang.kotlinstudyjetpack.base.BaseActivity
import cn.jiayang.kotlinstudyjetpack.databinding.ActivityMainBinding
import cn.jiayang.kotlinstudyjetpack.entity.Place
class MainActivity : BaseActivity<ActivityMainBinding>() {


    private val mViewModel: MainViewModel by lazy {
        setViewModel<MainViewModel> {
            MainViewModelFactory(MainRepository())
        }
    }


    private val mAdapter: MainAdapter by lazy { MainAdapter() }

    override fun initActivity(savedInstanceState: Bundle?) {

        mBindingView.recyclerView.layoutManager = LinearLayoutManager(this)
        mBindingView.recyclerView.adapter = mAdapter

        mBindingView.searchPlaceEdit.addTextChangedListener (afterTextChanged = {

            val mEditStr = it.toString()
            if (mEditStr.isNotEmpty()) {

                mViewModel.searchPlace(mEditStr)
            } else {
                mBindingView.recyclerView.visibility = View.GONE
                mBindingView.bgImageView.visibility = View.VISIBLE
                mAdapter.clearAll()
            }
        })

        mViewModel.mPlaces.observe(this, Observer {
            if (!it.isNullOrEmpty()) {

                mBindingView.recyclerView.visibility = View.VISIBLE
                mBindingView.bgImageView.visibility = View.GONE
                mAdapter.setNewDataAll(it as MutableList<Place>)
            } else {
                Toast.makeText(this, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
            }
        })
    }

}

