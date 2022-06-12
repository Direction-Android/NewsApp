package uz.direction.news.data.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import uz.direction.news.data.model.News
import uz.direction.news.data.network.news.NewsService

class NewsRepository(private val newsService: NewsService) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val newsLiveData:MutableLiveData<News> = MutableLiveData()

    fun getNews(country: String){
        coroutineScope.launch {
            val newsResponse = newsService.getNews(country)
            if (newsResponse.isSuccessful){
                newsLiveData.postValue(newsResponse.body())
            }
        }
    }

    fun cancelJob(){
        coroutineScope.cancel()
    }
}