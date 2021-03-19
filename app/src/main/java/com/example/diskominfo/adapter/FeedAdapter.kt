package com.example.diskominfo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diskominfo.DetailActivity
import com.example.diskominfo.ItemClickListener
import com.example.diskominfo.Model.Channel
import com.example.diskominfo.R
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
        return true
    }
}
class FeedAdapter(private val rssObjects: Channel,private val mContext:Context) : RecyclerView.Adapter<FeedViewHoler>() {

    private val inlater: LayoutInflater
    init {
        inlater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHoler {
        val itemView = inlater.inflate(R.layout.row,parent,false)
        return FeedViewHoler(itemView)
    }

    override fun onBindViewHolder(holder: FeedViewHoler, position: Int) {

        holder.txtTitle.text = rssObjects.items[position].title
        holder.image = rssObjects.items[position].image
        holder.description.text = rssObjects.items[position].description

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java).apply {
        }
        it.context.startActivity(intent)
    }

//        holder.setItemClickListener(ItemClickListener{view, position, isLongCLick ->
//
//            if (!isLongCLick) {
//                val browserIntent =
//                    Intent(Intent.ACTION_VIEW, Uri.parse(rssObjects.items[position].link))
//                mContext.startActivity(browserIntent)
//            }
//
//        })
    }


    override fun getItemCount(): Int {
        return rssObjects.items.size

    }
}