package com.parthdesai.myapplication.presentation.business_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parthdesai.myapplication.R
import com.parthdesai.myapplication.data.models.BusinessDto
import java.util.HashMap

class ParentAdapter(
    private val context: Context,
    private val categoryList: List<String>,
    private val mapOfBusinesses: HashMap<String, MutableList<BusinessDto>>
) : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

    private lateinit var childAdapter: ChildAdapter

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val categoryName = categoryList[position]
        var size = "0"

        val businessDto: MutableList<BusinessDto>? = mapOfBusinesses[categoryName]
        size = businessDto?.size.toString()
        businessDto?.let {
            childAdapter = ChildAdapter(
                businessDto,
                context
            )
            val layoutManager = LinearLayoutManager(context)
            holder.businessRecyclerView.layoutManager = layoutManager
            holder.businessRecyclerView.adapter = childAdapter
        }

        holder.categoryName.text = "$categoryName ($size)"
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryName: TextView = view.findViewById(R.id.categoryName)
        val businessRecyclerView: RecyclerView = view.findViewById(R.id.rvBusinesses)
    }
}