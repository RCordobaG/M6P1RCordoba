package com.rcordoba.m6p1rcordoba.ui

import androidx.recyclerview.widget.RecyclerView
import com.rcordoba.m6p1rcordoba.R
import com.rcordoba.m6p1rcordoba.data.db.model.OrderEntity
import com.rcordoba.m6p1rcordoba.databinding.OrderElementBinding

class OrderViewHolder (private val binding: OrderElementBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(order : OrderEntity){
        var tableNumber : Int
        binding.apply {
            orderNumberRecyclerTextView.text = order.id.toString()
            mainCourseRecyclerTextView.text = order.mainCourse
            beverageRecyclerTextView.text = order.beverage
            dessertRecyclerTextView.text = order.dessert
            //tableNumberTextView.text = order.tableNumber.toString()
            waiterRecyclerTextView.text = order.waiterName
            tableNumber = order.tableNumber + 1
            if(tableNumber == 1)
            {
                tableNumberTextView.setImageResource(R.drawable.table_1)
            }
            else if (tableNumber == 2)
            {
                tableNumberTextView.setImageResource(R.drawable.table_2)
            }
            else if (tableNumber == 3)
            {
                tableNumberTextView.setImageResource(R.drawable.table_3)
            }
            else if (tableNumber == 4)
            {
                tableNumberTextView.setImageResource(R.drawable.table_4)
            }
        }
    }
}