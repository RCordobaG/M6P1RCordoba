package com.rcordoba.m6p1rcordoba.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rcordoba.m6p1rcordoba.data.db.model.OrderEntity
import com.rcordoba.m6p1rcordoba.databinding.OrderElementBinding

class OrderAdapter (private val onOrderClicked : (OrderEntity) -> Unit) : RecyclerView.Adapter<OrderViewHolder>(){
    private var orders : List<OrderEntity> = emptyList()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : OrderViewHolder{
        val binding = OrderElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount(): Int = orders.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]

        holder.bind(order)

        holder.itemView.setOnClickListener{
            onOrderClicked(order)
        }
    }

    fun updateList(list: List<OrderEntity>){
        orders = list
        notifyDataSetChanged()
    }
}