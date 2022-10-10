package com.example.android_basic_manager.view.order

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_basic_manager.R
import com.example.android_basic_manager.model.DataInformation
import com.example.android_basic_manager.model.DataItems
import java.text.NumberFormat



open  class AdapterDetaiOrder( val detailItems: MutableList<DataItems>) : RecyclerView.Adapter<AdapterDetaiOrder.ViewHodel>(){

    class ViewHodel(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nameProduct : TextView
        var priceProduct : TextView
        var imageProduct : ImageView
        var sizeProduct : TextView

        init {
            nameProduct = itemView.findViewById(R.id.NameProduct)
            priceProduct = itemView.findViewById(R.id.PriceProduct)
            imageProduct = itemView.findViewById(R.id.Image_Items)
            sizeProduct = itemView.findViewById(R.id.SizeProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodel {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_detail_order, parent, false)
        return ViewHodel(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHodel, position: Int) {
        holder.nameProduct.text = "Sản Phẩm : ${detailItems[position].name_Product}"
        holder.sizeProduct.text = "Size : ${detailItems[position].Size}"
        holder.priceProduct.text = "Giá : ${converMonney(detailItems[position].Price)}"
        Glide.with(holder.itemView.context)
            .load(detailItems[position].Image)
            .into(holder.imageProduct)

    }

    override fun getItemCount(): Int {
        return detailItems.size
    }

    fun converMonney(Total: String?): String? {

        var total = Total?.toLong()
        // conver price dạng long về dạng tiền tệ
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.setMaximumFractionDigits(0)
        val convert = numberFormat.format(total)
        return convert
    }
}