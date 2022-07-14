package uz.direction.news.data.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.direction.news.data.model.News

object RetrofitService {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.run {
                proceed(request()
                    .newBuilder()
                    .addHeader("Authorization", "d2ade8f4e9e447cea11450191b581348")
                    .build())
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