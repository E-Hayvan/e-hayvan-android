package com.ehayvan.app.modules.viewprofile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ehayvan.app.R
import com.ehayvan.app.databinding.RowContent1Binding
import com.ehayvan.app.modules.viewprofile.`data`.model.Content1RowModel
import kotlin.Int
import kotlin.collections.List

class ContentAdapter(
  var list: List<Content1RowModel>
) : RecyclerView.Adapter<ContentAdapter.RowContent1VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowContent1VH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_content1,parent,false)
    return RowContent1VH(view)
  }

  override fun onBindViewHolder(holder: RowContent1VH, position: Int) {
    val content1RowModel = Content1RowModel()
    // TODO uncomment following line after integration with data source
    // val content1RowModel = list[position]
    holder.binding.content1RowModel = content1RowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Content1RowModel>) {
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
      item: Content1RowModel
    ) {
    }
  }

  inner class RowContent1VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowContent1Binding = RowContent1Binding.bind(itemView)
  }
}
