package uz.direction.news.data.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.direction.news.R
import uz.direction.news.data.model.Article
import uz.direction.news.data.model.News

class NewsAdapter
    :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var articles = emptyList<Article>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(articles[position])
    }

    override fun getItemCount() = articles.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemImage: ImageView
        private val itemTitle: TextView
        private val itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_details)
        }

        fun onBind(article: Article) {
            itemTitle.text = article.title
            itemDetail.text = article.content
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<Article>) {
        articles = newList
        notifyDataSetChanged()

    }

}