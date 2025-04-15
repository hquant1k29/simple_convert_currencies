package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ItemAdapter.ItemViewHolder

class ItemAdapter(val items: MutableList<ItemModel>, val listener: ItemClickListener? = null): RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ItemViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item.hoten
        holder.textMssv.text = item.mssv
        holder.imageView.setImageResource(item.avatarResId)

        holder.checkBox.setOnCheckedChangeListener(null) // Xoá listener cũ để tránh lỗi recycle
        holder.checkBox.isChecked = item.selected

        // Cập nhật trạng thái khi người dùng thay đổi checkbox
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.selected = isChecked
        }
    }

    override fun getItemCount() = items.size
    fun removeSelectedStudents() {
        items.removeAll { it.selected }
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View, val listener: ItemClickListener? = null): RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.image_avatar)
        val textView = itemView.findViewById<TextView>(R.id.text_hoten)
        val textMssv = itemView.findViewById<TextView>(R.id.text_mssv)
        val checkBox = itemView.findViewById<android.widget.CheckBox>(R.id.check_selected)

        init {
            itemView.setOnClickListener {
                listener?.onItemClicked(adapterPosition)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClicked(position: Int)
    }
}