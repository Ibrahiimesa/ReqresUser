package com.esa.reqresuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.esa.reqresuser.data.network.response.DataUser
import com.esa.reqresuser.databinding.ItemUserBinding

class UserAdapter: PagingDataAdapter<DataUser, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataUser>() {
            override fun areItemsTheSame(oldItem: DataUser, newItem: DataUser): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataUser, newItem: DataUser): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null){
            holder.bind(data)
        }
    }

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataUser) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .into(binding.imgAvatar)
                tvEmail.text = user.email
                tvName.text = "${user.firstName} ${user.lastName}"
            }
        }
    }
}