package com.project.kotlinappapi.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.kotlinappapi.R
import com.project.kotlinappapi.models.User

class UsersAdapter (private var dataList: List<User>)
    : RecyclerView.Adapter<UsersAdapter.HolderDataTransaksi>() {
    class HolderDataTransaksi(v: View) : RecyclerView.ViewHolder(v) {
        val name = v.findViewById<TextView>(R.id.name)
        val email = v.findViewById<TextView>(R.id.email)
        val id = v.findViewById<TextView>(R.id.id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderDataTransaksi {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return HolderDataTransaksi(v)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: HolderDataTransaksi, position: Int) {
        val data = dataList.get(position)
        holder.id.setText(data.id)
        holder.email.setText(data.email)
        holder.name.setText(data.name)
    }

    fun setData(newDataList: List<User>) {
        dataList = newDataList
        notifyDataSetChanged()
    }
}