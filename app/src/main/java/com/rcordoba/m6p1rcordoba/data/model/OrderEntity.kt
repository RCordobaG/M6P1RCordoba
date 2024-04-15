package com.rcordoba.m6p1rcordoba.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rcordoba.m6p1rcordoba.Constants

@Entity(tableName = Constants.DATABASE_ORDER_TABLE)
data class OrderEntity (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "orderNumber")
    val id: Long = 0,
    @ColumnInfo (name = "mainCourse")
    var mainCourse : String,
    @ColumnInfo (name = "beverage")
    var beverage : String,
    @ColumnInfo (name = "dessert")
    var dessert : String,
    @ColumnInfo (name = "table")
    var tableNumber : Int
)