package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class StudentAdapter(private val context: Context, private val students: MutableList<StudentModel>) : BaseAdapter() {
    override fun getCount(): Int = students.size
    override fun getItem(position: Int): Any = students[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.student_item_layout, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        val student = students[position]
        viewHolder.textHoten.text = student.hoten
        viewHolder.textMssv.text = student.mssv

        viewHolder.btnDelete.setOnClickListener {
            students.removeAt(position)
            notifyDataSetChanged()
        }

        return view
    }

    private class ViewHolder(view: View) {
        val textHoten: TextView = view.findViewById(R.id.text_hoten)
        val textMssv: TextView = view.findViewById(R.id.text_mssv)
        val btnDelete: Button = view.findViewById(R.id.btn_delete)
    }
}