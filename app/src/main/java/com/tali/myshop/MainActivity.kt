package com.tali.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tali.myshop.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onResume() {
        super.onResume()
        getProducts()
    }
   private fun getProducts(){
        var retrofit =ApiClient.buildApiClient(ApiInterface::class.java)
        var request =retrofit.getProducts()
        request.enqueue(object : Callback<ProductsResponse>{
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>,
            ) {
             if(response.isSuccessful){
                 var product =response.body()?.product
                 var adapter=adapter(product?: emptyList())
                 Toast.makeText(baseContext,"fetched ${product?.size}Product",Toast.LENGTH_LONG).show()
                 binding.rvrecyclerview.layoutManager=LinearLayoutManager(this@MainActivity)
                 binding.rvrecyclerview.adapter =adapter
             }
                else{
                    Toast.makeText(baseContext,response.errorBody()?.string(),Toast.LENGTH_LONG).show()
             }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }

        })
    }
}