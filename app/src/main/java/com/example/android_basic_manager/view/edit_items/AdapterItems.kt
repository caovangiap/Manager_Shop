package com.example.android_basic_manager.view.edit_items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide

import com.example.android_basic_manager.R
import com.example.android_basic_manager.model.AccessoryData
import java.text.NumberFormat

class AdapterItems(val data : MutableList<AccessoryData>) : RecyclerView.Adapter<AdapterItems.ViewHodler>()  {

    lateinit var onclick : Setonclick

    interface Setonclick{
        fun onclick(position: Int){
        }
    }

    fun listener (listener: Setonclick){
        onclick = listener
    }

    class ViewHodler(items: View, click : Setonclick) : RecyclerView.ViewHolder(items)
    {
        val imageView: ImageView
        val name: TextView
        val price: TextView
        val Button : Button

        init {
            imageView = items.findViewById(R.id.accessory)
            name = items.findViewById(R.id.name)
            price = items.findViewById(R.id.price)
            Button = items.findViewById(R.id.click)

            Button.setOnClickListener{
                click.onclick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodler {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_accessory, parent, false)

        return ViewHodler(view, onclick)
    }

    override fun onBindViewHolder(holder: ViewHodler, position: Int) {
        // conver price o dang long ve dang tien te
        val price = data[position].Price?.toLong()
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.setMaximumFractionDigits(0)
        val convert = numberFormat.format(price)

        holder.price.setText(convert)
        holder.name.setText(data[position].Name)
        Glide.with(holder.itemView.context)
            .load(data[position].Image)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}