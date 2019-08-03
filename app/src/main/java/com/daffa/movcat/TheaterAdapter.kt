package com.daffa.movcat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.rv_theater.view.*

class TheaterAdapter(context: Context?, private val data :List<TheaterModel>) : RecyclerView.Adapter<TheaterHolder>() {
    private val mContext : Context? = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheaterHolder {
        return TheaterHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_theater,parent,false))
    }

    override fun onBindViewHolder(holder: TheaterHolder, position: Int) {
        holder.bindTheater(data[position], mContext)
    }

    override fun getItemCount(): Int = data.size
}
class TheaterHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    private val ivTheater = itemView.ivTheater
    private val tvName = itemView.tvName
    private val tvAddress = itemView.tvAddress
    private val tvSchedule = itemView.tvSchedule
    fun bindTheater(data: TheaterModel, context: Context?){
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(10))
        context?.let { context ->
            tvName.text = data.name
            tvAddress.text = data.address
            tvSchedule.text = data.schedule
            Glide.with(context)
                .load(data.theater)
                .error(R.drawable.err)
                .apply(requestOptions)
                .into(ivTheater)
        }
    }
}
