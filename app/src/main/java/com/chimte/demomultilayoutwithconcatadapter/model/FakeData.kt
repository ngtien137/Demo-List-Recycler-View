package com.chimte.demomultilayoutwithconcatadapter.model

import androidx.lifecycle.MutableLiveData

object FakeData {

    val firstItems = MutableLiveData(ArrayList<String>().apply {
        for (i in 0..9) {
            add("Item #${size}")
        }
    })

    val secondItems = MutableLiveData(ArrayList<String>().apply {
        for (i in 0..9) {
            add("Item #${size}")
        }
    })

    val thirdsItems = MutableLiveData(ArrayList<String>().apply {
        for (i in 0..100) {
            add("Item #${size}")
        }
    })

}