package com.project.kotlinappapi.data.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.project.kotlinappapi.R
import com.project.kotlinappapi.models.Book
import com.project.kotlinappapi.views.BookActivity
import com.project.kotlinappapi.views.BookUpdate

class BooksAdapter(private var dataList: List<Book>, private val parent: BookActivity)
    : RecyclerView.Adapter<BooksAdapter.HolderDataTransaksi>() {
    class HolderDataTransaksi(v: View) : RecyclerView.ViewHolder(v) {
        val name = v.findViewById<TextView>(R.id.name)
        val desc = v.findViewById<TextView>(R.id.description)
        val id = v.findViewById<TextView>(R.id.id)
        val date = v.findViewById<TextView>(R.id.date)
        val us_name = v.findViewById<TextView>(R.id.name_user)
        val id_user = v.findViewById<TextView>(R.id.id_user)
        val btn = v.findViewById<LinearLayout>(R.id.btn_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderDataTransaksi {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return HolderDataTransaksi(v)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: HolderDataTransaksi, position: Int) {
        val data = dataList.get(position)
        holder.id.setText(data.id)
        holder.desc.setText(data.description)
        holder.name.setText(data.name)
        holder.date.setText(data.date_publish)
        holder.us_name.setText(data.user.name)
        holder.id_user.setText(data.user.id)

        holder.btn.setOnClickListener {
            val frag = BookUpdate()

            val bundle = Bundle()
            bundle.putString("id", data.id)
            frag.arguments = bundle

            frag.show(parent.supportFragmentManager, "form")
        }

        holder.btn.setOnLongClickListener {
            parent.deleteBook(data.id)
            true
        }
    }

    fun setData(newDataList: List<Book>) {
        dataList = newDataList
        notifyDataSetChanged()
    }
}