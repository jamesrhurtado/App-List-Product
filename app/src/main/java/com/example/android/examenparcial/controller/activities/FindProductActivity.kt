package com.example.android.examenparcial.controller.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.examenparcial.R
import com.example.android.examenparcial.adapter.ProductAdapter
import com.example.android.examenparcial.models.Product
import com.example.android.examenparcial.network.ApiResponseDetails
import com.example.android.examenparcial.network.ProductService
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_find.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FindProductActivity: AppCompatActivity() {

    lateinit var products: List<Product>
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)
        btBuscar.setOnClickListener {
            searchProduct()
        }
    }
    private fun searchProduct() {
        val btBuscar: Button = findViewById(R.id.btBuscar)
        val productName = findViewById<TextInputEditText>(R.id.etName)
        val productNameValue = productName.text.toString()
        recyclerView = findViewById<RecyclerView>(R.id.rvFind)
        recyclerView.layoutManager= LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/food/products/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productService: ProductService = retrofit.create(ProductService::class.java)
        val request=productService.getProduct("979b107215934f4786cde0970b5cfe0a", productNameValue)

        request.enqueue(object : Callback<ApiResponseDetails> {
            override fun onFailure(call: Call<ApiResponseDetails>, t: Throwable) {
                Log.d("Activity Fail", "Error: $t")
            }
            override fun onResponse(
                call: Call<ApiResponseDetails>,
                response: Response<ApiResponseDetails>
            ) {
                if(response.isSuccessful){
                    val products: List<Product> = response.body()!!.products?:ArrayList()
                    recyclerView.adapter= ProductAdapter(products)
                    //Log.d("Activity successful", "Products: "+products.isEmpty())

                } else{
                    Log.d("Activity fail", "Error: "+response.message())
                }
            }
        })
    }

}