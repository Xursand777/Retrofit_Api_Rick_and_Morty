package com.x7.retrofit_api_rick_and_morty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.x7.retrofit_api_rick_and_morty.databinding.ActivityMainBinding
import com.x7.retrofit_api_rick_and_morty.model.Result
import com.x7.retrofit_api_rick_and_morty.model.RickandMortyModel
import com.x7.retrofit_api_rick_and_morty.retrofit.RetrofitIntance
import com.x7.retrofit_api_rick_and_morty.retrofit.RetrofitIntance.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var productAdapter: ProductAdapter
    var pagecounter=0
    var arrayList=ArrayList<com.x7.retrofit_api_rick_and_morty.model.Result>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Retrofitni chaqirib olish onScolled siz

//        val call:Call<RickandMortyModel> = api.getAllProducts()
//        call.enqueue(object :Callback<RickandMortyModel>{
//            override fun onResponse(
//                call: Call<RickandMortyModel>,
//                response: Response<RickandMortyModel>
//            ) {
//                if (response.isSuccessful){
//                    val res=response.body()
//                    arrayList=res!!.results as ArrayList<Result>
//
//                    binding.recyclerview1.layoutManager=GridLayoutManager(this@MainActivity,2)
//                    productAdapter=ProductAdapter(this@MainActivity,arrayList)
//                    binding.recyclerview1.adapter=productAdapter
//                }else{
//
//
//                }
//            }
//
//            override fun onFailure(call: Call<RickandMortyModel>, t: Throwable) {
//
//
//            }
//
//        })


        val gridlayoutmanager= GridLayoutManager(this@MainActivity,2)
        binding.recyclerview1.layoutManager= gridlayoutmanager
        productAdapter = ProductAdapter(this@MainActivity,arrayList)
        binding.recyclerview1.adapter = productAdapter


        // Pastdan Yuqoriga tortganda yangi rasmlarlar qoshiladi onScrolled funksiyasi

        binding.apply {
            recyclerview1.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    var pastvidibleitem=gridlayoutmanager.findLastCompletelyVisibleItemPosition()
                    var totalitemcount=gridlayoutmanager.itemCount
                    title="$totalitemcount $pastvidibleitem"
                    if (pastvidibleitem==totalitemcount-1){
                        title="$totalitemcount $pastvidibleitem End Game"
                        gorequest(pagecounter)
                    }
                }
            })
        }

        gorequest(pagecounter)

    }



    //Retrofitni chaqirib olish onScolled li

    fun gorequest(page:Int){
       binding.progressbar1.visibility= View.VISIBLE

        val call: Call<RickandMortyModel> = RetrofitIntance.api.getAlldata(page)
        call.enqueue(object : Callback<RickandMortyModel> {
            override fun onResponse(
                call: Call<RickandMortyModel>,
                response: Response<RickandMortyModel>
            ) {
                if (response.isSuccessful){
                  binding.progressbar1.visibility=View.GONE
                    productAdapter.addresults(response.body()!!.results as ArrayList<Result>)
                    pagecounter++

                }

            }

            override fun onFailure(call: Call<RickandMortyModel>, t: Throwable) {

            }

        })
    }
}