package com.example.month_5_lesson3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.month_5_lesson3.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter = PixaAdapter(arrayListOf())
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {
        with(binding) {
            imageRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        binding.doRequest(++page)
                    }
                }
            })
            changePageBtn.setOnClickListener {
                doRequest(++page)
            }
            enterBtn.setOnClickListener {
                doRequest(page = 1)
            }
        }
    }

    private fun ActivityMainBinding.doRequest(page: Int) {
        RetrofitService.api.getImage(pictureWord = searchEd.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        adapter.addData(response.body()?.Hits!!)
                        imageRecyclerView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", t.message.toString())
                }
            })
    }
}
