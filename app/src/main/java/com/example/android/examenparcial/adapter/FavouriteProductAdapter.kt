package com.example.android.examenparcial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.examenparcial.R
import com.example.android.examenparcial.database.ProductDB
import com.example.android.examenparcial.models.Product
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.prototype_favourite.view.*
import kotlinx.android.synthetic.main.prototype_product.view.ivProduct
import kotlinx.android.synthetic.main.prototype_product.view.tvName

class FavouriteProductAdapter(private val movies: List<Product>, private val context: Context): RecyclerView.Adapter<FavouriteProductAdapter.ViewHolder>(){
    lateinit var products: List<Product>
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.tvName
        val ivImage = view.ivProduct
        val fabRemove =view.faRemove
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context)
            .inflate(R.layout.prototype_favourite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product=products[position]
        holder.tvName.text=product.title
        val picBuilder = Picasso.Builder(holder.itemView.context)
        picBuilder.downloader(OkHttp3Downloader(holder.itemView.context))
        picBuilder.build().load(product.image).into(holder.ivImage)

        holder.fabRemove.setOnClickListener{
            deleteProduct(product)
        }
    }

    private fun deleteProduct(product: Product) {
        ProductDB.getInstance(this.context).getProductDAO().deleteProduct(product)
        Toast.makeText(this.context, "Product deleted", Toast.LENGTH_SHORT).show();
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}