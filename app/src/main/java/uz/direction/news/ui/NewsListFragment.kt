package uz.direction.news.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.direction.news.R
import uz.direction.news.ui.adapter.NewsAdapter
import uz.direction.news.data.model.Article
import uz.direction.news.data.network.RetrofitService
import uz.direction.news.data.repository.NewsRepository
import uz.direction.news.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment(R.layout.fragment_news_list) {
    private val binding by viewBinding(FragmentNewsListBinding::bind)
    private val repository = NewsRepository(RetrofitService.newsService)
    private var news: List<Article> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            repository.getNews("us")
        }
        repository.newsLiveData.observe(viewLifecycleOwner) { news ->
            this@NewsListFragment.news = news.articles
            val newsAdapter = NewsAdapter(news.articles) { position -> onItemClick(position)  }

            binding.newsRecycleView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = newsAdapter
                adapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
            }

        }
    }

    private fun onItemClick(position: Int) {
        val action = NewsListFragmentDirections.actionNewsListToNewsOverview(newsArticle = news[position])
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.cancelJob()
    }
}