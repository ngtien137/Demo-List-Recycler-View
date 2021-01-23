package com.chimte.demomultilayoutwithconcatadapter.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.baselibrary.adapter.BaseAdapter
import com.base.baselibrary.adapter.viewholder.ViewHolderBase
import com.chimte.demomultilayoutwithconcatadapter.R
import com.chimte.demomultilayoutwithconcatadapter.databinding.ItemGroupItemBinding
import com.chimte.demomultilayoutwithconcatadapter.model.ItemGroupData
import com.chimte.demomultilayoutwithconcatadapter.utils.AppConst

class AdapterGroupItem(val itemGroupData: ItemGroupData, val layoutType: Int) :
    BaseAdapter<ItemGroupData>(R.layout.item_group_item) {

    override fun onBindViewHolder(holder: ViewHolderBase, position: Int) {
        val binding = holder.binding
        val context = binding.root.context
        if (binding is ItemGroupItemBinding) {
            binding.item = itemGroupData
            binding.rvItems.layoutManager = when (layoutType) {
                AppConst.LAYOUT_TYPE_LINEAR_VERTICAL -> {
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
                AppConst.LAYOUT_TYPE_GRID_3 -> {
                    GridLayoutManager(context, 3)
                }
                else -> {
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return 1
    }


}