package com.pixelart.githubapiusers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pixelart.githubapiusers.R
import com.pixelart.githubapiusers.common.GlideApp
import com.pixelart.githubapiusers.data.dto.AllUsers

class UsersAdapter(private val listener: OnItemClickedListener): ListAdapter<AllUsers, UsersAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.users_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.apply {
            setContent(user)
            itemView.setOnClickListener { listener.onItemClicked(position) }
        }
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        private val userName: TextView = view.findViewById(R.id.tvUserName)
        private val avatar: ImageView = view.findViewById(R.id.ivAvatar)

        fun setContent(user: AllUsers){
            userName.text = user.login
            GlideApp.with(view.context)
                .load(user.avatarUrl)
                .into(avatar)
        }
    }

    interface OnItemClickedListener{
        fun onItemClicked(position: Int)
    }

    companion object{
        val DIFF_CALLBACK: DiffUtil.ItemCallback<AllUsers> =
            object : DiffUtil.ItemCallback<AllUsers>(){
                override fun areItemsTheSame(oldItem: AllUsers, newItem: AllUsers): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: AllUsers, newItem: AllUsers): Boolean {
                    return oldItem == newItem
                }
            }
    }
}