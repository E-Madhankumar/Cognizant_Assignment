package com.assignment.view.adapter

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.assignment.R
import com.assignment.model.RowsItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.layout_facts_placeholder.view.*


class FactsAdapter(private var itemList: MutableList<RowsItem>) : RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    class ViewHolder(val context: Context, view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.tv_title
        val poster: ImageView = view.iv_imageHref
        val description:TextView = view.tv_description

        fun bind(item: RowsItem){
            title.text = item.title
            description.text = item.description
            if(item.imageHref !=null){
                Glide
                    .with(context)
                    .asBitmap()
                    .load(item.imageHref)
                    .error(R.drawable.ic_launcher_background)
                    .into(poster)
            }else{
                poster?.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.context,
            LayoutInflater.from(parent.context).inflate(R.layout.layout_facts_placeholder,parent,false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun update(moreContentItemList: MutableList<RowsItem>){
        val index = itemList.size
        itemList = moreContentItemList
        notifyItemInserted(index)
    }

}