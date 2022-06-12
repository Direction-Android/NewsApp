package uz.direction.news.appearance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import uz.direction.news.R
import uz.direction.news.databinding.FragmentNewsOverviewBinding

class NewsOverview : Fragment() {

    private lateinit var binding: FragmentNewsOverviewBinding
    private val args: NewsOverviewArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsOverviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide
            .with(this)
            .load(args.newsImage)
            .placeholder(R.drawable.img)
            .into(binding.newsOverviewImage)
        binding.apply {
            newsOverviewTitle.text = args.newsTitle
            newsOverviewPublisher.text = args.newsPublisher
            newsOverviewPublishedAt.text = args.newsPublishedAt
            newsOverviewContent.text = args.newsContent
        }
    }
}