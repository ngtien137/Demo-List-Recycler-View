package com.chimte.demomultilayoutwithconcatadapter.ui.home

import com.chimte.demomultilayoutwithconcatadapter.R
import com.chimte.demomultilayoutwithconcatadapter.databinding.FragmentHomeBinding
import com.chimte.demomultilayoutwithconcatadapter.ui.BaseMainFragment

class HomeFragment : BaseMainFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initBinding() {

    }

    override fun initView() {

    }

    override fun onViewClick(vId: Int) {
        when (vId) {
            R.id.btnConcatAdapter -> {
                navigateTo(R.id.action_homeFragment_to_concatAdapterFragment)
            }
        }
    }

}