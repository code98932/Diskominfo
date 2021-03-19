package com.example.diskominfo.Model

data class Item(val image:Int,val title:String, val link:String,val guid:String, val description:String,
                val items:List<Item>)