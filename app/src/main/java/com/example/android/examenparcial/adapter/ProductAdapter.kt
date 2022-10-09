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
import kotlinx.android.synthetic.main.prototype_product.view.*

class ProductAdapter(private val products: List<Product>):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.tvName
        val tvId = view.tvId
        val ivImage = view.ivProduct
        val fabAdd =view.fabAdd
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.tvName.text=product.title
        holder.tvId.text = product.id.toString()
        val picBuilder = Picasso.Builder(holder.itemView.context)
        picBuilder.downloader(OkHttp3Downloader(holder.itemView.context))
        picBuilder.build().load(product.image).into(holder.ivImage)

        holder.fabAdd.setOnClickListener{
            saveProduct(product, holder.itemView.context)
        }
    }

    private fun saveProduct(product: Product, context: Context) {
        ProductDB.getInstance(context).getProductDAO().insertProduct(product)
        Toast.makeText(context, "Product has been added to favourites", Toast.LENGTH_SHORT).show();
    }

    override fun getItemCount(): Int {
        return products.size
    }
}