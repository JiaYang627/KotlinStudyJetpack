package cn.jiayang.kotlinstudyjetpack.ui.place

import android.os.Bundle
import android.view.View
import cn.jiayang.kotlinstudyjetpack.base.BaseFragment
import cn.jiayang.kotlinstudyjetpack.databinding.FragmentPlaceBinding

/**
 * @author ：张 奎
 * @date ：2020-04-28 17：20
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class PlaceFragment : BaseFragment<FragmentPlaceBinding>() {



//    override fun getViewBinding(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        mBinding = FragmentPlaceBinding.inflate(inflater, container, false)
//        return if (mBinding != null) {
//            mBinding!!.root.apply { (parent as? ViewGroup)?.removeView(this) }
//        }else super.onCreateView(inflater, container, savedInstanceState)!!
//    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
    }

}