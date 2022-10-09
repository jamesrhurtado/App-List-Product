package com.example.android.examenparcial.controller.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.examenparcial.R
import com.example.android.examenparcial.adapter.FavouriteProductAdapter
import com.example.android.examenparcial.database.ProductDB
import com.example.android.examenparcial.models.Product
import kotlinx.android.synthetic.main.activity_find.*

class FavouriteProductActivity: AppCompatActivity()  {
    lateinit var products: List<Product>
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        loadFavouriteProducts()
    }

    private fun loadFavouriteProducts() {
        recyclerView = findViewById<RecyclerView>(R.id.rvFavourite)
        products = ProductDB.getInstance(this).getProductDAO().getAllProducts()
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= FavouriteProductAdapter(products)
    }
}