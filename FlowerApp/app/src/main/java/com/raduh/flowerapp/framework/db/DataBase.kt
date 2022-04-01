package com.raduh.flowerapp.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [OrderEntity::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class DataBase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "flowerOrders.db"
        private var instance: DataBase? = null

        private fun create(context: Context): DataBase =
            Room.databaseBuilder(context, DataBase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context): DataBase =
            synchronized(this) {
                (instance ?: create(context)).also { instance = it }
            }

    }

    abstract fun ordersDao(): OrdersDao
}