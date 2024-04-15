package com.rcordoba.m6p1rcordoba.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rcordoba.m6p1rcordoba.Constants
import com.rcordoba.m6p1rcordoba.data.db.model.OrderEntity

@Dao
interface OrderDAO {

    @Insert
    suspend fun insertOrder(order : OrderEntity)

    @Update
    suspend fun updateOrder(order : OrderEntity)

    @Delete
    suspend fun deleteOrder(order : OrderEntity)

    @Query ("SELECT * FROM ${Constants.DATABASE_ORDER_TABLE}")
    suspend fun getAllOrders() : List<OrderEntity>
}