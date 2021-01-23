package com.chimte.demomultilayoutwithconcatadapter.model

import androidx.recyclerview.widget.RecyclerView

data class ItemGroupData(var listName: String, var adapter: RecyclerView.Adapter<*>) {

}