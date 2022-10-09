package com.example.android.examenparcial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.examenparcial.models.Product


@Database(entities=[Product::class], version=1)
abstract class ProductDB: RoomDatabase() {
    abstract fun getProductDAO(): ProductDao
    companion object {
        private var INSTANCE: ProductDB?=null
        fun getInstance(context: Context):ProductDB{
            if(INSTANCE==null){
                INSTANCE= Room
                    .databaseBuilder(context, ProductDB::class.java, "products.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as ProductDB
        }
    }
}