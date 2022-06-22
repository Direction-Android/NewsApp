package uz.direction.news.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.direction.news.data.model.News

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getNews(@Query("country") country: Country): Response<News>
}