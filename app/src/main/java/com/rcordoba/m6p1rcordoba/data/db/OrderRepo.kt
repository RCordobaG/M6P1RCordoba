package com.rcordoba.m6p1rcordoba.data.db

import com.rcordoba.m6p1rcordoba.data.db.OrderDAO
import com.rcordoba.m6p1rcordoba.data.db.model.OrderEntity

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