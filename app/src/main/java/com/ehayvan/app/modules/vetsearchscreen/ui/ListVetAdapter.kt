package com.ehayvan.app.modules.vetsearchscreen.ui

import com.ehayvan.app.R
import com.ehayvan.app.modules.vetsearchscreen.data.model.ListVetModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.ehayvan.app.databinding.RowVetListBinding

class ListVetAdapter(private var list: List<ListVetModel>) : RecyclerView.Adapter<ListVetAdapter.RowListPetVH>() {
    private var clickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListPetVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_vet_list, parent, false)
        return RowListPetVH(view)
    }

    override fun onBindViewHolder(holder: RowListPetVH, position: Int) {
        val listVetModel = list[position]
        if (list[position].isFound == true) {
            val greenBackground = ContextCompat.getDrawable(holder.itemView.context, R.drawable.rectangle_bg_green_600_border_gray_51_radius_14)
            holder.itemView.background = greenBackground
        }
        holder.binding.listVetModel = listVetModel
    }

    override fun getItemCount(): Int = list.size

    fun updateData(newData: List<ListVetModel>) {
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
            item: ListVetModel
        )
    }

    inner class RowListPetVH(view: View) : RecyclerView.ViewHolder(view) {
        val binding: RowVetListBinding = RowVetListBinding.bind(itemView)
        init {
            itemView.setOnClickListener {
                clickListener?.onItemClick(itemView, adapterPosition, list[adapterPosition])
            }
        }
    }
}