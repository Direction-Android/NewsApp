package uz.direction.news.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.direction.news.R
import uz.direction.news.data.model.Article

class NewsAdapter(
    private val newsList: List<Article>,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(
        view: View,
        private val onItemClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }

        val newsImageView: ImageView = view.findViewById(R.id.newsImage)
        val newsTitleView: TextView = view.findViewById(R.id.newsTitle)
        val newsTextView: TextView = view.findViewById(R.id.newsText)
        val newsPublisherView: TextView = view.findViewById(R.id.newsPublisher)
        val newsUpdatedTimeView: TextView = view.findViewById(R.id.newsUpdatedTime)
        override fun onClick(p0: View?) {
            val position = bindingAdapterPosition
            onItemClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_view, parent, false)
        return ViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewModel = newsList[position]
        holder.apply {
            newsImageView.setImageFromUrl(itemViewModel.urlToImage)
            newsTitleView.text = itemViewModel.title
            newsTextView.text = itemViewModel.description
            newsPublisherView.text = itemViewModel.author
            newsUpdatedTimeView.text = itemViewModel.publishedAt.subSequence(0, 10)
        }
    }

    override fun getItemCount(): Int = newsList.size

}

fun ImageView.setImageFromUrl(imageUrl: String?) {
    Glide
        .with(this)
        .load(imageUrl)
        .placeholder(R.drawable.img)
        .into(this)
}