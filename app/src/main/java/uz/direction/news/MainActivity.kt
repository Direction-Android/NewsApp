package uz.direction.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.direction.news.data.network.RetrofitService
import uz.direction.news.data.repository.NewsRepository
import uz.direction.news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val repository = NewsRepository(RetrofitService.newsService)
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository.getNews("us")

        repository.newsLiveData.observe(this){ news ->
            binding.newsHeader.text = news.articles[0].title
        }
    }
}