package com.example.android_basic_manager.view.respone_client

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_basic_manager.R
import com.example.android_basic_manager.view.order.AdapterInformation

class AdapterResponse(val dataId : MutableList<String>) : RecyclerView.Adapter<AdapterResponse.ViewHolder>() {
    // click button mở ra items đơn hàng
    interface ClickButton{
        fun itemsOrder(position: Int)
    }

    lateinit var clickButton : ClickButton

    fun setClick(listenter : ClickButton){
        clickButton = listenter
    }

    class ViewHolder(itemView: View, click: ClickButton) : RecyclerView.ViewHolder(itemView){
        var name : TextView

        init {
            name = itemView.findViewById(R.id.Name)
            itemView.setOnClickListener {
                click.itemsOrder(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_response,parent,false)
        return ViewHolder(view, clickButton)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = dataId[position]
    }

    override fun getItemCount(): Int {
        return dataId.size
    }
}