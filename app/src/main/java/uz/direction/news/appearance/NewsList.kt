package uz.direction.news.appearance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.direction.news.appearance.rv.NewsAdapter
import uz.direction.news.data.model.Article
import uz.direction.news.data.network.RetrofitService
import uz.direction.news.data.repository.NewsRepository
import uz.direction.news.databinding.FragmentNewsListBinding

class NewsList : Fragment() {
    private lateinit var binding : FragmentNewsListBinding
    private val repository = NewsRepository(RetrofitService.newsService)
    private lateinit var news : List<Article>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository.getNews("us")
        repository.newsLiveData.observe(viewLifecycleOwner){ news ->
            this@NewsList.news = news.articles
            val newsAdapter = NewsAdapter(news.articles){ position -> onItemClick(position) }

            binding.newsRecycleView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = newsAdapter
            }
        }
    }

    private fun onItemClick(position: Int){
        val action = NewsListDirections.actionNewsListToNewsOverview(
            newsTitle = news[position].title,
            newsPublisher = news[position].author,
            newsPublishedAt = news[position].publishedAt,
            newsContent = news[position].content,
            newsImage = news[position].urlToImage
        )

        findNavController().navigate(action)
    }
}