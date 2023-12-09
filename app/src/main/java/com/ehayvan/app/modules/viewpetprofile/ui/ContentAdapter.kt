package com.ehayvan.app.modules.viewpetprofile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ehayvan.app.R
import com.ehayvan.app.databinding.RowContentBinding
import com.ehayvan.app.modules.viewpetprofile.`data`.model.ContentRowModel
import kotlin.Int
import kotlin.collections.List

class ContentAdapter(
  var list: List<ContentRowModel>
) : RecyclerView.Adapter<ContentAdapter.RowContentVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowContentVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_content,parent,false)
    return RowContentVH(view)
  }

  override fun onBindViewHolder(holder: RowContentVH, position: Int) {
    val contentRowModel = ContentRowModel()
    // TODO uncomment following line after integration with data source
    // val contentRowModel = list[position]
    holder.binding.contentRowModel = contentRowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ContentRowModel>) {
    list = newData
    notifyDataSetChanged()
  }

  fun setOnItemClickListener(clickListener: OnItemClickListener) {
    this.clickListener = clickListener
  }

  interface OnItemClickListener {
    fun onItemClick(
      view: View,
      position: Int,
      item: ContentRowModel
    ) {
    }
  }

  inner class RowContentVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowContentBinding = RowContentBinding.bind(itemView)
  }
}
