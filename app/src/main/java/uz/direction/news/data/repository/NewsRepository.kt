package uz.direction.news.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import uz.direction.news.data.model.News
import uz.direction.news.data.network.news.NewsService

class NewsRepository(private val newsService: NewsService) : NewsRepositoryInterface {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val _newsLiveData: MutableLiveData<News> = MutableLiveData()
    override val newsLiveData: LiveData<News> = _newsLiveData

    override fun getNews(country: String) {
        coroutineScope.launch {
            val newsResponse = newsService.getNews(country)
            if (newsResponse.isSuccessful) {
                _newsLiveData.postValue(newsResponse.body())
            }
        }
    }

    override fun cancelJob() {
        coroutineScope.cancel()
    }
}