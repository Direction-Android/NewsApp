package uz.direction.news.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.direction.news.R
import uz.direction.news.ui.adapter.setImageFromUrl
import uz.direction.news.databinding.FragmentNewsOverviewBinding

class NewsOverviewFragment : Fragment(R.layout.fragment_news_overview) {

    private val binding by viewBinding(FragmentNewsOverviewBinding::bind)
    private val args: NewsOverviewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            newsOverviewImage.setImageFromUrl(args.newsArticle.urlToImage)
            newsOverviewTitle.text = args.newsArticle.title
            newsOverviewPublisher.text = args.newsArticle.author
            newsOverviewPublishedAt.text = args.newsArticle.publishedAt.subSequence(0, 10)
            newsOverviewContent.text = args.newsArticle.content
        }

        binding.linkToNews.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(args.newsArticle.url)))
        }
    }
}