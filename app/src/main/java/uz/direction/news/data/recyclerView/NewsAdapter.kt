package uz.direction.news.data.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        private val itemTitle: TextView = itemView.findViewById(R.id.item_title)
        private val itemDetail: TextView = itemView.findViewById(R.id.item_details)

        fun onBind(article: Article) {
            //Glide.with(itemView.context).load(article.urlToImage).placeholder(R.drawable.ic_launcher_background).into(itemImage)
            itemTitle.text = article.title
            itemDetail.text = article.content
        }
    }



    fun setData(newList: List<Article>) {
        articles = newList

    }

}