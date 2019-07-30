package yass.stephanie.com.cryptotracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class NewsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    //mock data
    var article1 = NewsArticle("one",
        "Here are 5 things to help you get better",
        "This is a beautiful thing bro",
        "four",
        "14 December 2018",
        "six")
    var newsList : ArrayList<NewsArticle> = arrayListOf(article1, article1, article1, article1, article1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        viewManager = LinearLayoutManager(this)
        viewAdapter = NewsAdapter(newsList)

        recyclerView = findViewById<RecyclerView>(R.id.news_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


    }
}
