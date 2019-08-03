package com.daffa.movcat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_item.view.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions



class RVAdapter(context : Context?,private val data : List<DataModel>) : RecyclerView.Adapter<MovieHolder>() {
    private val mContext : Context? = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return MovieHolder(v)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindMovie(data[position],mContext)
    }

    override fun getItemCount(): Int = data.size
}

class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle = itemView.tvTitle
    private val ivImage = itemView.ivImage
    private val preview = itemView.cvPreview
    private val tvRating = itemView.tvRating
    fun bindMovie(data: DataModel,context: Context?) {
        tvTitle.text=data.title
        tvRating.text=data.rating
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(10))
        context?.let { cont ->
            Glide.with(cont)
                .load(data.poster)
                .error(R.drawable.err)
                .apply(requestOptions)
                .into(ivImage)
        }
        preview.setOnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("Data",data )
            context?.startActivity(intent)
        }
    }
}