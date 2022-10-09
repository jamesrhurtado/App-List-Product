package com.example.android.examenparcial.controller.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.examenparcial.R
import com.example.android.examenparcial.adapter.FavouriteProductAdapter
import com.example.android.examenparcial.database.ProductDB
import com.example.android.examenparcial.models.Product
import kotlinx.android.synthetic.main.fragment_favourites.view.*

class FavouriteFragment : Fragment() {
    var products:List<Product> = ArrayList()
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        products = ProductDB.getInstance(view.context).getProductDAO().getAllProducts()
        recyclerView=view.rvFavourite
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter= FavouriteProductAdapter(products, view.context)
    }
}