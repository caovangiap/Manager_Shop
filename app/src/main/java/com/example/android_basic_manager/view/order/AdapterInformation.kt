package com.example.android_basic_manager.view.order

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_basic_manager.R
import com.example.android_basic_manager.model.DataInformation

class AdapterInformation(val dataInformation : MutableList<DataInformation>) : RecyclerView.Adapter<AdapterInformation.ViewHolder>() {

    // click button mở ra items đơn hàng
    interface ClickButton{
        fun itemsOrder(position: Int)
    }

    lateinit var clickButton : ClickButton

    fun setClick(listenter : ClickButton){
        clickButton = listenter
    }
    class ViewHolder(itemView: View,clickButon: ClickButton) : RecyclerView.ViewHolder(itemView) {
        val time : TextView
        val name : TextView
        val address : TextView
        val total : TextView

        init {
            time = itemView.findViewById(R.id.time)
            name = itemView.findViewById(R.id.name)
            address = itemView.findViewById(R.id.address)
            total = itemView.findViewById(R.id.total)
            itemView.setOnClickListener {
                clickButon.itemsOrder(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_information,parent,false)
        return ViewHolder(view,clickButton)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.total.text = "Tổng Tiền: ${dataInformation[position].total}"
        holder.time.text = "Đã Chuyển: ${dataInformation[position].complate}"
        holder.address.text = "Địa chỉ: ${dataInformation[position].address}"
        holder.name.text = "Tên người dùng: ${dataInformation[position].name}"
    }

    override fun getItemCount(): Int {
        return dataInformation.size
    }
}