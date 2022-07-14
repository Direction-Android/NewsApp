package uz.direction.news.data.adapter

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

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var articles = emptyList<Article>()
    private var onClick: ((Article, Int) -> Unit)? = null

    fun setOnClickListener(clickEvent: ((Article, Int) -> Unit)) {
        onClick = clickEvent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(articles[position], position, onClick ?: { article, i -> })
    }

    override fun getItemCount() = articles.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        private val itemTitle: TextView = itemView.findViewById(R.id.item_title)


        fun onBind(
            article: Article,
            position: Int,
            onClick: (Article, Int) -> Unit,
        ) {
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemImage)
            itemTitle.text = article.title
            itemView.setOnClickListener {
                onClick.invoke(article, position)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<Article>) {
        articles = newList
        notifyDataSetChanged()

    }


}