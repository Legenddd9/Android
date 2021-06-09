package com.example.eighthhomework.api.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eighthhomework.ProfileActivity
import com.example.eighthhomework.R
import com.example.eighthhomework.api.dto.User

class RecyclerViewAdapter(private val list: List<User>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    private var clickedId : Int = 0

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val imageView: ImageView = itemView.findViewById(R.id.userView)
        private val name : TextView = itemView.findViewById(R.id.name)
        private val email : TextView = itemView.findViewById(R.id.email)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Log.d("POSITION", "${position+7}")

                val intent = Intent(itemView.context, ProfileActivity::class.java).apply {
                    putExtra("position", position+7)
                }
                itemView.context.startActivity(intent)
            }
        }

        fun bindUser(user: User){

            Glide.with(itemView)
                .load(user.avatar)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)

            name.text = user.firstName + " " + user.lastName
            email.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindUser(list[position])
    }

    override fun getItemCount() = list.size
}