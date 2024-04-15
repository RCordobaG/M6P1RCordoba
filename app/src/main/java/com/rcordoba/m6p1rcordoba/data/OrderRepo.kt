package com.rcordoba.m6p1rcordoba.data

import com.rcordoba.m6p1rcordoba.data.OrderDAO
import com.rcordoba.m6p1rcordoba.data.model.OrderEntity

class OrderRepo (private val dao: OrderDAO){

    suspend fun createOrder(order: OrderEntity){
        dao.insertOrder(order)
    }

    suspend fun getAllOrders() : List<OrderEntity>{
        return dao.getAllOrders()
    }

    suspend fun updateOrder(order: OrderEntity){
        dao.updateOrder(order)
    }

    suspend fun deleteOrder(order: OrderEntity){
        dao.deleteOrder(order)
    }
}