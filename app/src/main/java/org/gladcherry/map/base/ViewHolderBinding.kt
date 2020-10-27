package org.gladcherry.map.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class ViewHolderBinding<out T: ViewDataBinding>(view:View):RecyclerView.ViewHolder(view) {
    val binding:T = DataBindingUtil.bind(view)!!
}