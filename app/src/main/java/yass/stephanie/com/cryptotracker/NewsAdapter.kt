package yass.stephanie.com.cryptotracker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NewsAdapter(private val data: ArrayList<NewsArticle>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val newsImage: TextView = view.findViewById(R.id.news_image)
        val newsHeaderText: TextView = view.findViewById(R.id.news_header_text)
        val newsTag: TextView = view.findViewById(R.id.news_tag)
        val newsDate: TextView = view.findViewById(R.id.news_date)
        val descriptionText: TextView = view.findViewById(R.id.news_description_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_homescreen_screen, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        holder.newsImage.text = data[position].imageUrl
        holder.newsHeaderText.text = data[position].title
        holder.newsTag.text = data[position].tag
        holder.newsDate.text = data[position].date
        holder.descriptionText.text = data[position].description
    }

    override fun getItemCount(): Int {
        return data.size
    }


}