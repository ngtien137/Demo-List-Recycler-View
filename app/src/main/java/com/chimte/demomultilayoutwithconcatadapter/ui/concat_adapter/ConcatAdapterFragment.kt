package com.chimte.demomultilayoutwithconcatadapter.ui.concat_adapter

import androidx.recyclerview.widget.ConcatAdapter
import com.base.baselibrary.adapter.BaseAdapter
import com.base.baselibrary.adapter.viewholder.ViewHolderBase
import com.base.baselibrary.views.ext.loge
import com.chimte.demomultilayoutwithconcatadapter.R
import com.chimte.demomultilayoutwithconcatadapter.adapter.AdapterGroupItem
import com.chimte.demomultilayoutwithconcatadapter.adapter.GridHeaderAdapter
import com.chimte.demomultilayoutwithconcatadapter.databinding.FragmentConcatAdapterBinding
import com.chimte.demomultilayoutwithconcatadapter.model.FakeData
import com.chimte.demomultilayoutwithconcatadapter.model.ItemGroupData
import com.chimte.demomultilayoutwithconcatadapter.ui.BaseMainFragment
import com.chimte.demomultilayoutwithconcatadapter.utils.AppConst

class ConcatAdapterFragment : BaseMainFragment<FragmentConcatAdapterBinding>() {

    //region properties
    var list1: List<String> = FakeData.firstItems.value ?: emptyList()
    var list2: List<String> = FakeData.secondItems.value ?: emptyList()
    var list3: List<String> = FakeData.thirdsItems.value ?: emptyList()

    private val adapter1 by lazy {
        BaseAdapter<String>(R.layout.item_string_linear)
    }

    private val adapter2 by lazy {
        BaseAdapter<String>(R.layout.item_string_linear)
    }

    private val adapter3 by lazy {
        object : BaseAdapter<String>(R.layout.item_string_grid) {
            override fun onBindViewHolder(holder: ViewHolderBase, position: Int) {
                super.onBindViewHolder(holder, position)
                loge("BindItem Grid: ${holder.adapterPosition}")
            }
        }
    }

    private lateinit var concatAdapter: ConcatAdapter

    private val listItemGroup by lazy {
        ArrayList<ItemGroupData>().apply {
            add(ItemGroupData("List 1", adapter1))
            add(ItemGroupData("List 2", adapter2))
            add(ItemGroupData("List 3", adapter3))
        }
    }

    //endregion

    override fun getLayoutId(): Int {
        return R.layout.fragment_concat_adapter
    }

    override fun initBinding() {
        concatAdapter = ConcatAdapter(
            AdapterGroupItem(listItemGroup[0], AppConst.LAYOUT_TYPE_LINEAR_HORIZONTAL),
            AdapterGroupItem(listItemGroup[1], AppConst.LAYOUT_TYPE_LINEAR_HORIZONTAL),
            AdapterGroupItem(listItemGroup[2], AppConst.LAYOUT_TYPE_GRID_3)
        )
        val adapter = GridHeaderAdapter(list1, list2, list3)
        adapter.attachToRecyclerView(binding.rvList)
    }

    override fun initView() {
        FakeData.firstItems.observe(viewLifecycleOwner, {
            adapter1.data = it
        })

        FakeData.secondItems.observe(viewLifecycleOwner, {
            adapter2.data = it
        })

        FakeData.thirdsItems.observe(viewLifecycleOwner, {
            adapter3.data = it
        })
    }

}