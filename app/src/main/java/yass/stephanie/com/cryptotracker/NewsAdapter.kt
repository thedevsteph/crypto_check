package yass.stephanie.com.cryptotracker

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class NewsAdapter(private val data: ArrayList<NewsArticle>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    companion object {
        private lateinit var context: Context
    }

    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val newsImage: ImageView = view.findViewById(R.id.news_image)
        val newsHeaderText: TextView = view.findViewById(R.id.news_header_text)
        val newsTag: TextView = view.findViewById(R.id.news_tag)
        val newsDate: TextView = view.findViewById(R.id.news_date)
        val descriptionText: TextView = view.findViewById(R.id.news_description_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_news_screen, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        context = holder.view.context
        var imageUrl: String? = data[position].imageUrl
        imageUrl?.let { getImage(imageUrl, holder.newsImage) }

        var listener = View.OnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].linkUrl))
            context.startActivity(intent)
        }

        holder.view.setOnClickListener(listener)
        holder.newsHeaderText.text = data[position].title
        holder.newsTag.text = "NEW"
        holder.newsDate.text = data[position].date
        holder.descriptionText.text = data[position].description
    }

    private fun getImage(url: String, view: ImageView) {
        Picasso.with(context)
            .load(url)
            .into(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }


}