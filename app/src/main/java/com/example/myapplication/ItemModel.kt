package com.example.myapplication

data class ItemModel(
    val avatarResId: Int,
    val hoten: String,
    val mssv: String,
    var selected: Boolean = false
)
