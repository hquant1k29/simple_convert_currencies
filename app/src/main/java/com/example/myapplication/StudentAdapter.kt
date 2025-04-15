package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class StudentAdapter(val students: MutableList<StudentModel>) : BaseAdapter() {
    override fun getCount(): Int = students.size
    override fun getItem(p0: Int) = students[p0]
    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(
        p0: Int,
        p1: View?,
        p2: ViewGroup?,
    ): View? {

        val itemView: View
        val viewHolder: ViewHolder

        if (p1 == null) {
            itemView = LayoutInflater.from(p2?.context).inflate(R.layout.student_item_layout, p2, false)
            viewHolder = ViewHolder()
            viewHolder.imageAvatar = itemView.findViewById<ImageView>(R.id.image_avatar)
            viewHolder.textHoten = itemView.findViewById<TextView>(R.id.text_hoten)
            viewHolder.textMssv = itemView.findViewById<TextView>(R.id.text_mssv)
            viewHolder.checkSelected = itemView.findViewById<CheckBox>(R.id.check_selected)
            itemView.tag = viewHolder
        } else {
            itemView = p1
            viewHolder = p1.tag as ViewHolder
        }

        val student = students[p0]
        viewHolder.imageAvatar.setImageResource(student.avatarResId)
        viewHolder.textHoten.text = student.hoten
        viewHolder.textMssv.text = student.mssv
        viewHolder.checkSelected.isChecked = student.selected

        viewHolder.checkSelected.setOnClickListener {
            student.selected = !student.selected
            notifyDataSetChanged()
        }

        return itemView
    }
    fun removeSelectedStudents() {
        students.removeAll { it.selected }
        notifyDataSetChanged()
    }

    class ViewHolder {
        lateinit var imageAvatar: ImageView
        lateinit var textHoten: TextView
        lateinit var textMssv: TextView
        lateinit var checkSelected: CheckBox
    }
}