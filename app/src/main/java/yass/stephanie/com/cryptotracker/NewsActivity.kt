package yass.stephanie.com.cryptotracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yass.stephanie.com.cryptotracker.services.CryptoNewsService
import java.util.*

class NewsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    companion object {
        private const val BASE_URL = "https://newsapi.org/"
        private const val CURRENCY_NAME = "CURRENCY_NAME"
        private const val FIXED_PAGE_SIZE: Int = 20
        private val currencyNewsList: ArrayList<NewsArticle> = arrayListOf()
        var currentCurrencyString: String? = null
    }

    //mock data
    var article1 = NewsArticle(
        "https://i.kinja-img.com/gawker-media/image/upload/s--fHe8gP1k--/c_fill,fl_progressive,g_center,h_900,q_80,w_1600/rri1pmjir9iyc4u2ter6.png",
        "Here are 5 things to help you get better",
        "This is a beautiful thing bro",
        "four",
        "14 December 2018",
        "six"
    )
    var newsList: ArrayList<NewsArticle> = arrayListOf(article1, article1, article1, article1, article1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        currentCurrencyString = intent.getStringExtra(CURRENCY_NAME)
        viewManager = LinearLayoutManager(this)
        viewAdapter = NewsAdapter(currencyNewsList)

        recyclerView = findViewById<RecyclerView>(R.id.news_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        currencyNewsList.clear()
        getNewsData()
    }

    private fun getNewsData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoNewsService::class.java)


        //pass the name from the previous activity


        val call = service.getCryptoNewsInformation(currentCurrencyString!!, FIXED_PAGE_SIZE)
        call.enqueue(object : Callback<CryptoNewsResponse> {

            override fun onResponse(call: Call<CryptoNewsResponse>, response: Response<CryptoNewsResponse>) {
                if (response.code() == 200) {
                    val cryptoNewsResponse = response.body()
                    Gson().toJson(cryptoNewsResponse?.articles.apply {
                        this?.let {
                            createCurrencyNewsListFromData(this)
                            recyclerView.adapter?.notifyDataSetChanged()
                        }

                    })
                }
            }

            override fun onFailure(call: Call<CryptoNewsResponse>, t: Throwable) {
                Toast.makeText(this@NewsActivity, "Error getting news", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun createCurrencyNewsListFromData(data: ArrayList<Article>) {
        data?.let {

            for (item in data) {
                var title = item.title
                var description = item.description
                var url = item.url
                var imageUrl = item.urlImage
                var date: String? = item.date

                val newArticle = NewsArticle(imageUrl, title, description, url, date)
                currencyNewsList.add(newArticle)
            }
        }

    }
}


