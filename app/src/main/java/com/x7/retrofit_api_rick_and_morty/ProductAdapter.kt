package com.x7.retrofit_api_rick_and_morty

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.x7.retrofit_api_rick_and_morty.databinding.RecyclerviewItemBinding
import com.x7.retrofit_api_rick_and_morty.model.Result


class ProductAdapter constructor(
    val context: Context,
    val arrayList: ArrayList<Result>
):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var checknewresult=ArrayList<com.x7.retrofit_api_rick_and_morty.model.Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view=RecyclerviewItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.textviewrick.text=arrayList.get(position).name
        Glide.with(context).load(arrayList.get(position).image).into(holder.binding.imageviewrick)
    }

    override fun getItemCount(): Int =arrayList.size

    class ProductViewHolder(val binding: RecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root)

    fun addresults(newresults:ArrayList<Result>){
        if (checknewresult!=newresults){
            arrayList.addAll(newresults)
            notifyDataSetChanged()
        }
        checknewresult=newresults

    }
}