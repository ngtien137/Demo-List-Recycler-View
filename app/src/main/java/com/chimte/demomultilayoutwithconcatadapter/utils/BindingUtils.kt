package com.chimte.demomultilayoutwithconcatadapter.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.base.baselibrary.adapter.BaseAdapter
import com.base.baselibrary.adapter.BaseListAdapter
import com.base.baselibrary.adapter.SuperAdapter
import com.base.baselibrary.utils.getAppDimension
import com.base.baselibrary.utils.getAppString
import com.base.baselibrary.utils.onDebouncedClick
import com.base.baselibrary.utils.player.AppPlayer
import com.base.baselibrary.views.custom.CustomViewPager
import com.base.baselibrary.views.rv_touch_helper.ItemTouchHelperExtension
import com.base.baselibrary.views.rv_touch_helper.VerticalDragTouchHelper
import com.bumptech.glide.Glide
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import java.io.File
import java.lang.Exception

@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int {
    return if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("pager_set_adapter")
fun ViewPager.setOwnAdapter(adapter: FragmentStatePagerAdapter?) {
    adapter?.let {
        setAdapter(adapter)
    }
}

@BindingAdapter("tv_apply_height")
fun TextView.applyHeightToNone(height: Float) {
    layoutParams.height = height.toInt()
}


@BindingAdapter("tv_apply_marquee")
fun TextView.applyMarquee(apply: Boolean?) {
    post {
        apply?.let {
            if (it) {
                ellipsize = TextUtils.TruncateAt.MARQUEE
                isSelected = true
            }
        }
    }
}

@BindingAdapter("tv_get_file_name_from_path")
fun TextView.setNameFromFile(path: String?) {
    path?.let {
        val file = File(it)
        text = file.name
    }
}

@BindingAdapter("sw_checked_listener")
fun CompoundButton.applyCheckedListener(checkedListener: CompoundButton.OnCheckedChangeListener?) {
    setOnCheckedChangeListener(checkedListener)
}

@BindingAdapter("translate_from_bottom")
fun View.translateFromBottom(isOpen: Boolean?) {
    post {
        if (translationY.toInt() == height && isOpen == true) {
            animate().translationY(0f)
        } else if (translationY.toInt() == 0 && isOpen != true) {
            animate().translationY(height.toFloat())
        }
    }
}

@BindingAdapter("translate_from_end")
fun View.translateFromEnd(isOpen: Boolean?) {
    post {
        if (translationX.toInt() == width && isOpen == true) {
            animate().translationY(0f)
        } else if (translationX.toInt() == 0 && isOpen != true) {
            animate().translationY(width.toFloat())
        }
    }
}

@BindingAdapter("debounceClick")
fun View.onDebouncedClick(listener: View.OnClickListener?) {
    listener?.let {
        this.onDebouncedClick {
            listener.onClick(this)
        }
    }

}

@BindingAdapter("longClick")
fun View.onLongClickView(listener: View.OnLongClickListener) {
    setOnLongClickListener { v -> listener.onLongClick(v) }
}

@BindingAdapter("touchClick")
fun View.onTouchClickView(listener: View.OnTouchListener) {
    setOnTouchListener { v, ev ->
        listener.onTouch(v, ev)
    }
}

@BindingAdapter("rv_apply_item_touch_helper")
fun RecyclerView.applyItemTouchHelper(itemTouchHelperExtension: ItemTouchHelperExtension?) {
    itemTouchHelperExtension?.attachToRecyclerView(this)
}

@BindingAdapter("rv_vertical_drag")
fun RecyclerView.enableVerticalDrag(enable: Boolean?) {
    enable?.let {
        adapter?.let {
            if (adapter is SuperAdapter<*>) {
                val callback = VerticalDragTouchHelper(adapter as SuperAdapter<*>)
                ItemTouchHelper(callback).attachToRecyclerView(this)
            }
        }
    }
}

@BindingAdapter("rv_snap_linear")
fun RecyclerView.attachLinearSnapHelper(snap: Boolean? = true) {
    if (snap == true) {
        LinearSnapHelper().attachToRecyclerView(this)
    }
}

@BindingAdapter("rv_set_adapter")
fun <T : Any> RecyclerView.applyAdapter(applyAdapter: BaseAdapter<T>?) {
    applyAdapter?.apply {
        adapter = applyAdapter
    }
}

@BindingAdapter("rv_set_adapter")
fun <T : RecyclerView.ViewHolder> RecyclerView.applyAdapter(applyAdapter: RecyclerView.Adapter<T>?) {
    applyAdapter?.apply {
        adapter = applyAdapter
    }
}

@BindingAdapter("rv_set_adapter")
fun <T : Any> RecyclerView.applyAdapter(applyAdapter: SuperAdapter<T>?) {
    applyAdapter?.apply {
        adapter = applyAdapter
    }
}

@BindingAdapter("rv_set_adapter")
fun <T : Any> RecyclerView.applyListAdapter(applyAdapter: BaseListAdapter<T>?) {
    applyAdapter?.apply {
        adapter = applyAdapter
    }
}

@BindingAdapter("rv_set_fix_size")
fun RecyclerView.setFixSize(set: Boolean?) {
    setHasFixedSize(set ?: false)
}

@BindingAdapter("img_set_drawable_id")
fun ImageView.setDrawableById(id: Int) {
    setImageResource(id)
}

@BindingAdapter("anim_visible")
fun View.startAnimVisible(visible: Int?) {
    if (visible == null) {
        return
    }
    if (visible == View.VISIBLE && this.visibility != View.VISIBLE) {
        this.visibility = visible
        alpha = 0f
        animate().alpha(1f)
    } else if ((visible == View.GONE && this.visibility != View.GONE) || (visible == View.INVISIBLE && this.visibility != View.INVISIBLE)) {
        alpha = 1f
        animate().alpha(0f)
        postDelayed({
            this.visibility = visible
        }, 300)
    }
}

@BindingAdapter("pager_swipe_able")
fun CustomViewPager.setSwipeAble(swipe: Boolean?) {
    swipe?.let {
        isAbleSwipe = it
    }
}


//Your binding write below

@BindingAdapter("glide_load_drawable")
fun ImageView.glideLoadDrawable(drawable: Drawable?) {
    drawable?.let {
        post {
            Glide.with(this).load(drawable).override(width, height).into(this)
        }
    }
}

@BindingAdapter("glide_load_path")
fun ImageView.glideLoadPath(path: String?) {
    path?.let {
        post {
            Glide.with(this).load(path).override(width, height).into(this)
        }
    }
}

@BindingAdapter("glide_load_uri")
fun ImageView.glideLoadPath(uri: Uri?) {
    uri?.let {
        post {
            Glide.with(this).load(uri).override(width, height).into(this)
        }
    }
}

@BindingAdapter("glide_load_drawable_id")
fun ImageView.glideLoadDrawable(id: Int?) {
    id?.let {
        post {
            Glide.with(this).load(id).override(width, height)
                .into(this)
        }
    }
}

@BindingAdapter("img_load_drawable_id")
fun ImageView.imgLoadDrawableById(id: Int?) {
    id?.let {
        setImageResource(id)
    }
}

@BindingAdapter("ivLoadBackgroundById")
fun ImageView.imgLoadColorById(id: Int) {
    setBackgroundResource(id)
}

@BindingAdapter("tv_set_underline_text")
fun TextView.tvSetUnderlineText(text: String?) {
    val content = SpannableString(text ?: "")
    content.setSpan(UnderlineSpan(), 0, content.length, 0)
    setText(content)
}

@BindingAdapter("tv_set_info_text_title", "tv_set_info_text_content")
fun TextView.tvSetInfoText(title: String?, content: String?) {
    val infoText = "<font color='#616161'>$title:</font><font color='#212121'> $content</font>"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        setText(
            Html.fromHtml(infoText, Html.FROM_HTML_MODE_LEGACY),
            TextView.BufferType.SPANNABLE
        )
    } else {
        setText(Html.fromHtml(infoText), TextView.BufferType.SPANNABLE);
    }
}

