package com.example.diskominfo.Model

data class Channel(val title:String, val link:String, val description:String, val language:String,
                   val creator:String, val rights:String, val items:List<Item>)
