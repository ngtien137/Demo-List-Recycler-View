package com.chimte.demomultilayoutwithconcatadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.base.baselibrary.activity.BaseActivity
import com.chimte.demomultilayoutwithconcatadapter.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}