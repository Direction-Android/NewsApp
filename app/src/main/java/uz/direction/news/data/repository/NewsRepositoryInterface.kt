package uz.direction.news.data.repository

import androidx.lifecycle.LiveData
import uz.direction.news.data.model.News

interface NewsRepositoryInterface {
    val newsLiveData: LiveData<News>
    fun getNews(country: String)
    fun cancelJob()
}