package com.example.diskominfo.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diskominfo.R
import com.example.diskominfo.`interface`.ItemClickListener
import java.util.*

class FeedViewHoler(itemView: View):RecyclerView.ViewHolder(itemView),
    View.OnClickListener,View.OnLongClickListener {

    var txtTitle: TextView
    var image: Int
    var description: TextView

    private var itemClickListener: ItemClickListener? = null

    init {
        txtTitle = itemView.findViewById(R.id.txt_title) as TextView
        image = itemView.findViewById(R.id.image) as Int
        description = itemView.findViewById(R.id.info) as TextView

        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener!!.onClick(v, adapterPosition, false)

    }

    override fun onLongClick(v: View?): Boolean {
        itemClickListener!!.onClick(v, adapterPosition, true)
    }
}
class FeedAdapter(private val rssObjects: Objects,private val mContext:Context)//
    : RecyclerView.Adapter<FeedViewHoler>()