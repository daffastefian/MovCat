package com.daffa.movcat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.rv_star.view.*

class StarAdapter(context: Context?,private val star : ArrayList<String>) :RecyclerView.Adapter<StarHolder>() {
    private val mContext :Context? =context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarHolder {
        return StarHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_star,parent,false))
    }

    override fun onBindViewHolder(holder: StarHolder, position: Int) {
        holder.bindStar(star[position],mContext)
    }

    override fun getItemCount(): Int = star.size
}
class StarHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bindStar(data: String,context: Context?) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(10))
        context?.let { cont ->
            Glide.with(cont)
                .load(data)
                .error(R.drawable.err)
                .apply(requestOptions)
                .into(itemView.ivStar)
        }
    }
}