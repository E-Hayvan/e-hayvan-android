package com.ehayvan.app.modules.petownerdashboard.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ehayvan.app.R
import com.ehayvan.app.databinding.RowPetListBinding
import com.ehayvan.app.modules.petownerdashboard.data.model.ListPetRowModel
import kotlin.collections.List

class ListPetAdapter(
    private var list: List<ListPetRowModel>
) : RecyclerView.Adapter<ListPetAdapter.RowListPetVH>() {
    private var clickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListPetVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_pet_list, parent, false)
        return RowListPetVH(view)
    }

    override fun onBindViewHolder(holder: RowListPetVH, position: Int) {
        val listPetRowModel = list[position]
        // Uncomment the following line after integration with data source
        // val listPetRowModel = list[position]
        holder.binding.listPetRowModel = listPetRowModel
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    public fun updateData(newData: List<ListPetRowModel>) {
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
            item: ListPetRowModel
        ) {
        }
    }

    inner class RowListPetVH(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val binding: RowPetListBinding = RowPetListBinding.bind(itemView)
        init {
            itemView.setOnClickListener {
                clickListener?.onItemClick(itemView, adapterPosition, list[adapterPosition])
            }
        }
    }
}