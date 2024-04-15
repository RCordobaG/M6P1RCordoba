package com.rcordoba.m6p1rcordoba.ui

import androidx.recyclerview.widget.RecyclerView
import com.rcordoba.m6p1rcordoba.data.db.model.OrderEntity
import com.rcordoba.m6p1rcordoba.databinding.OrderElementBinding

class OrderViewHolder (private val binding: OrderElementBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(order : OrderEntity){
        binding.apply {
            orderNumberRecyclerTextView.text = order.id.toString()
            mainCourseRecyclerTextView.text = order.mainCourse
            beverageRecyclerTextView.text = order.beverage
            dessertRecyclerTextView.text = order.dessert
            tableNumberTextView.text = order.tableNumber.toString()
        }
    }
}