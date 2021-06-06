package com.example.midterm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.midterm2.api.RetrofitClient
import com.example.midterm2.api.adapter.RecyclerViewAdapter
import com.example.midterm2.api.dto.AboutProduct
import com.example.midterm2.api.dto.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var total: TextView
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refresh = findViewById(R.id.refreshLayout);
        
        refresh.setOnRefreshListener{
            fetchProducts()
        }

        fetchProducts()

        val products =  App.instance.db.getProductsDao().getAllProducts()
        recyclerView = findViewById(R.id.recyclerView)
        total = findViewById(R.id.textView2)
        if (products != null) {
            total.text = "Total : " + products.size.toString()
            recyclerViewAdapter = RecyclerViewAdapter(products.toList())
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun fetchProducts() {
        refresh.isRefreshing = true;
        RetrofitClient.gorestApiForAll.getProducts()
            .enqueue(object : Callback<AboutProduct<List<Product>>> {
                override fun onResponse(
                    call: Call<AboutProduct<List<Product>>>,
                    response: Response<AboutProduct<List<Product>>>
                ) {
                    if (response.isSuccessful && response.body() != null){
                        refresh.isRefreshing = false
                        response.body()!!.data.forEach{ i -> App.instance.db.getProductsDao().insert(i)}
                    }
                }

                override fun onFailure(call: Call<AboutProduct<List<Product>>>, t: Throwable) {
                    refresh.isRefreshing = false
                    Log.d("Fail", "FAILED")
                }
            })
    }

   /* private fun getData(): List<Product> {
        val products = ArrayList<Product>()

        products.add(Product(0,"TEST","TEST","https://dneegypt.nyc3.digitaloceanspaces.com/2018/09/Product.jpg"))
        products.add(Product(1,"PRODUCT1","PRODUCT - 1","https://cdn.corporatefinanceinstitute.com/assets/products-and-services-1024x1024.jpeg"))
        products.add(Product(2,"PRODUCT2","PRODUCT - 2","https://ml3tegr94pkt.i.optimole.com/FyfRwSA-pYa3bhH9/w:auto/h:auto/q:80/https://www.webtopic.com/wp-content/uploads/2019/09/Sell-products-online-why-should-I-start-selling-online-1.jpg"))
        products.add(Product(3,"PRODUCT3","PRODUCT - 3","https://miro.medium.com/max/4480/1*KaxYIFOdDGnBtsqsO6gYyA.png"))

        return  products
    }*/
}
