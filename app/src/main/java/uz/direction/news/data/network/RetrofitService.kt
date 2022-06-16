package uz.direction.news.data.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.direction.news.data.network.news.NewsService

object RetrofitService {

    private val client = OkHttpClient.Builder()
        .addInterceptor() { chain ->
            chain.run {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader("Authorization", "a70acb8ec16341e4813ead7c858a8b71")
                        .build()
                )
            }
        }
        .build()

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://newsapi.org/")
            .build()

    val newsService: NewsService = retrofit.create(NewsService::class.java)

}