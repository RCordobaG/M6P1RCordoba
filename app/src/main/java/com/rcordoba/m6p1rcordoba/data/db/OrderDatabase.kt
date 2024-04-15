package com.rcordoba.m6p1rcordoba.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rcordoba.m6p1rcordoba.Constants
import com.rcordoba.m6p1rcordoba.data.db.model.OrderEntity

@Database(
    entities = [OrderEntity::class],
    version = 2,
    exportSchema = true
)
abstract class OrderDatabase : RoomDatabase() {

    abstract fun orderDAO(): OrderDAO

    companion object{

        @Volatile
        private var INSTANCE : OrderDatabase? = null

        fun getDatabase(context: Context): OrderDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OrderDatabase::class.java,
                    Constants.DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}