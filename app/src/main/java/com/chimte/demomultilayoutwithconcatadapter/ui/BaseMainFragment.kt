package com.chimte.demomultilayoutwithconcatadapter.ui

import androidx.databinding.ViewDataBinding
import com.base.baselibrary.fragment.BaseNavigationFragment
import com.chimte.demomultilayoutwithconcatadapter.MainActivity

abstract class BaseMainFragment<BD : ViewDataBinding> : BaseNavigationFragment<BD, MainActivity>() {
}
