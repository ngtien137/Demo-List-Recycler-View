package com.chimte.demomultilayoutwithconcatadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.baselibrary.adapter.BaseAdapter
import com.base.baselibrary.adapter.viewholder.ViewHolderBase
import com.base.baselibrary.views.ext.loge
import com.chimte.demomultilayoutwithconcatadapter.R
import com.chimte.demomultilayoutwithconcatadapter.databinding.ItemGroupItemBinding
import com.chimte.demomultilayoutwithconcatadapter.databinding.ItemStringGridBinding
import com.chimte.demomultilayoutwithconcatadapter.model.ItemGroupData

const val TYPE_HORIZONTAL = 1
const val TYPE_GRID = 3

class GridHeaderAdapter(
    val list1: List<String>, val list2: List<String>, val list3: List<String>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (position <= 1) return TYPE_HORIZONTAL
        return TYPE_GRID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == TYPE_HORIZONTAL) {
            return LinearViewHolder(
                ItemGroupItemBinding.bind(
                    inflater.inflate(R.layout.item_group_item, parent, false)
                )
            )
        }
        return GridViewHolder(
            ItemStringGridBinding.bind(
                inflater.inflate(R.layout.item_string_grid, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        loge("Bind holder $position ${holder.javaClass.simpleName}")
        var realPosition = position - 2
        if (realPosition < 0) realPosition = 0
        when (holder) {
            is LinearViewHolder -> {
                val data = if (position == 0) list1 else list2
                holder.bind(data)
            }
            is GridViewHolder -> {
                val data = list3[realPosition]
                holder.bind(data)
            }
        }
    }

    override fun getItemCount(): Int {
        var size = list3.size
        if (list1.isNotEmpty()) ++size
        if (list2.isNotEmpty()) ++size
        return size
    }

    fun attachToRecyclerView(recyclerView: RecyclerView) {
        val spanCount = 3
        val layoutManager = GridLayoutManager(recyclerView.context, spanCount)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (getItemViewType(position) == TYPE_HORIZONTAL) return spanCount
                return 1
            }
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = this
    }

    class GridViewHolder(val binding: ItemStringGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            binding.item = data
        }
    }

    class LinearViewHolder(val binding: ItemGroupItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val adapter by lazy {
            object: BaseAdapter<String>(R.layout.item_string_linear) {
                override fun onBindViewHolder(holder: ViewHolderBase, position: Int) {
                    super.onBindViewHolder(holder, position)
                    loge("Bind linear $position")
                }
            }
        }

        fun bind(data: List<String>) {
            adapter.data = data
            val layoutManager = LinearLayoutManager(
                binding.root.context, LinearLayoutManager.HORIZONTAL, false
            )
            binding.rvItems.layoutManager = layoutManager
            binding.rvItems.adapter = adapter
            binding.item = ItemGroupData("List data", adapter)
        }
    }
}