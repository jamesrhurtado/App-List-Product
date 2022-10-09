package com.example.android.examenparcial.database

import androidx.room.*
import com.example.android.examenparcial.models.Product

@Dao
interface ProductDao {
    @Insert
    fun insertProduct(vararg product: Product)
    @Query("SELECT * FROM products")
    fun getAllProducts(): List<Product>
    @Delete
    fun deleteProduct(vararg product: Product)
    @Update
    fun updateProduct(vararg product: Product)
    @Query("SELECT * FROM products WHERE title LIKE :search_query || '%'")
    fun loadParticularProduct(search_query: String?): List<Product>
}