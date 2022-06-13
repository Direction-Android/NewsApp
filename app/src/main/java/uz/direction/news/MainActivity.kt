package uz.direction.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.direction.news.data.network.NewsService
import uz.direction.news.data.network.RetrofitService
import uz.direction.news.data.recyclerView.NewsAdapter
import uz.direction.news.data.repository.NewsRepository
import uz.direction.news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val myAdapter by lazy { NewsAdapter() }


    private val repository = NewsRepository(RetrofitService.newsService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        repository.getNews("us")
        repository.newsLiveData.observe(this) { response ->
            myAdapter.setData(response.articles)
        }
    }


    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = myAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        repository.cancelJob()
    }
}
