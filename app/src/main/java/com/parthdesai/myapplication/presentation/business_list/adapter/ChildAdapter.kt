package com.parthdesai.myapplication.presentation.business_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parthdesai.myapplication.R
import com.parthdesai.myapplication.data.models.BusinessDto
import timber.log.Timber
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import androidx.core.content.ContextCompat
import com.parthdesai.myapplication.presentation.business_detail.BusinessDetailActivity


class ChildAdapter(
    private val businesses: List<BusinessDto>,
    private val context: Context
) : RecyclerView.Adapter<ChildAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.child_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val business = businesses[position]
        holder.childBusinessName.text = business.name
        holder.childBusinessRating.text = "Rating: ${business.rating}"
        Glide
            .with(holder.itemView.context)
            .load(business.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.childBusinessImage)

        holder.childView.setOnClickListener {
            val intent = Intent(context, BusinessDetailActivity::class.java)
            intent.putExtra("business", business)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return businesses.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val childBusinessName: TextView = view.findViewById(R.id.childBusinessName)
        val childBusinessRating: TextView = view.findViewById(R.id.childBusinessRating)
        val childBusinessImage: ImageView = view.findViewById(R.id.childBusinessImage)
        val childView: LinearLayout = view.findViewById(R.id.childView)
    }
}