package com.example.midterm2.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midterm2.R
import com.example.midterm2.api.dto.Product
import org.w3c.dom.Text

class RecyclerViewAdapter(private val list: List<Product> ) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val imageView: ImageView = itemView.findViewById(R.id.productView)
        private val name : TextView = itemView.findViewById(R.id.name)
        private val describtion : TextView = itemView.findViewById(R.id.describtion)

        fun bindProduct(product: Product){
            Glide   .with(itemView)
                    .load(product.image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView)

            name.text = product.name
            describtion.text = product.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindProduct(list[position])
    }

    override fun getItemCount() = list.size
}