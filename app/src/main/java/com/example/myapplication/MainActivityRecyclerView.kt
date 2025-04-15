package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivityRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recycler)

        val items = mutableListOf<ItemModel>()
        repeat(28) {
            items.add(ItemModel(
                avatarResId = resources.getIdentifier("thumb$it", "drawable", packageName),
                hoten = "Item $it",
                mssv = "SV $it"
            ))
        }

        val adapter = ItemAdapter(items, object: ItemAdapter.ItemClickListener {
            override fun onItemClicked(position: Int) {
                Log.v("TAG", "onItemClicked: $position")
            }
        })
        // Log.d("RecyclerView", "Tổng số item trong adapter: ${items.size}")

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val name: EditText = findViewById(R.id.name)
        val mssv: EditText = findViewById(R.id.mssv)
        findViewById<Button>(R.id.button_add).setOnClickListener {
            items.add(0, ItemModel(R.drawable.thumb13, name.text.toString(),mssv.text.toString()))
            adapter.notifyItemInserted(0)
        }

        findViewById<Button>(R.id.button_delete).setOnClickListener {
            adapter.removeSelectedStudents()
//            items.removeAt(it.selected)
//            adapter.notifyItemRemoved(it.selected)
        }

//        findViewById<Button>(R.id.button_update).setOnClickListener {
//            items[1].hoten = "Updated Item"
//            adapter.notifyItemChanged(1)
//        }
    }
}