package com.rcordoba.m6p1rcordoba.application

import android.app.Application
import com.rcordoba.m6p1rcordoba.data.OrderRepo
import com.rcordoba.m6p1rcordoba.data.OrderDatabase

class OrdersDBApp: Application() {
    private val database by lazy {
        OrderDatabase.getDatabase(this@OrdersDBApp)

    }

    val repository by lazy {
        OrderRepo(database.orderDAO())
    }
}