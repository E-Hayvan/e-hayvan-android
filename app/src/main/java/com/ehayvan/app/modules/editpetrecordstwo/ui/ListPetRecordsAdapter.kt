package com.ehayvan.app.modules.editpetrecordstwo.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ehayvan.app.R
import com.ehayvan.app.databinding.RowPetRecordsBinding
import com.ehayvan.app.modules.editpetrecordstwo.data.model.ListPetRecordsRowModel
import kotlin.collections.List

class ListPetRecordsAdapter(
    private var list: List<ListPetRecordsRowModel>
) : RecyclerView.Adapter<ListPetRecordsAdapter.RowListPetRecordsVH>() {
    private var clickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListPetRecordsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_pet_records, parent, false)
        return RowListPetRecordsVH(view)
    }

    override fun onBindViewHolder(holder: RowListPetRecordsVH, position: Int) {
        val listPetRecordsRowModel = list[position]
        // Uncomment the following line after integration with data source
        // val listPetRecordsRowModel = list[position]
        holder.binding.listPetRecordsRowModel = listPetRecordsRowModel
    }

    override fun getItemCount(): Int = list.size

    public fun updateData(newData: List<ListPetRecordsRowModel>) {
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
            item: ListPetRecordsRowModel
        ) {
        }
    }

    inner class RowListPetRecordsVH(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val binding: RowPetRecordsBinding = RowPetRecordsBinding.bind(itemView)
        init {
            itemView.setOnClickListener {
                clickListener?.onItemClick(itemView, adapterPosition, list[adapterPosition])
            }
        }
    }
}