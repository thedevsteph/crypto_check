package yass.stephanie.com.cryptotracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yass.stephanie.com.cryptotracker.services.CryptoDataService
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    //mock data
    var bitcoin = Currencies("BTC", "34.444", "Bitcoin", 34.33)
    var ethereum = Currencies("ETH", "134.03", "Ethereum", 34.33)
    var fakeData : ArrayList<Currencies> = arrayListOf(bitcoin, ethereum, ethereum, bitcoin, ethereum)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getCurrencyData()
        setContentView(R.layout.activity_main)
        viewManager = LinearLayoutManager(this)
        viewAdapter = HomeAdapter(currencyListData)

        recyclerView = findViewById<RecyclerView>(R.id.home_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun getCurrencyData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoDataService::class.java)

        for (item in CurrencyList::class.java.declaredFields) {

            val call = service.getCryptoInformation(item.name)
            call.enqueue(object : Callback<CryptoDataResponse> {
                override fun onResponse(call: Call<CryptoDataResponse>, response: Response<CryptoDataResponse>) {
                    if (response.code() == 200) {
                        val cryptoDataResponse = response.body()
                        Gson().toJson(cryptoDataResponse?.result.apply {
                            var currencyCode = item.name
                            this?.let {
                                createCurrencyListFromData(this, currencyCode)
                                recyclerView.adapter?.notifyDataSetChanged()
                            }
                        })
                    }
                }

                override fun onFailure(call: Call<CryptoDataResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Unable to load data", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }


    private fun createCurrencyListFromData(mappedData: Map<String, JsonObject>, currencyCode: String) {
        mappedData?.let {
            var result = it[currencyCode]
            result?.let { data ->

                val priceFormat = DecimalFormat("#0.0000")
                var rawPrice = data[QUOTE].asJsonObject.get(USD).asJsonObject.get(PRICE).asDouble
                val price = priceFormat.format(rawPrice)

                val percentageChangeFormat = DecimalFormat("#0.000")
                var rawPercentageChange =
                    data[QUOTE].asJsonObject.get(USD).asJsonObject.get(PERCENTAGE_CHANGE_1H).asDouble
                val hourlyPercentageChange = percentageChangeFormat.format(rawPercentageChange).toDouble()

                var shortName = data.get(SYMBOL).asString
                var longName = data.get(SLUG).asString
                currencyListData.add(Currencies(shortName, price, longName, hourlyPercentageChange))
            }
        }

    }


    companion object {
        private const val BASE_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest/"
        private var currencyListData: ArrayList<Currencies> = arrayListOf()
        private const val QUOTE: String = "quote"
        private const val SYMBOL: String = "symbol"
        private const val SLUG: String = "slug"
        private const val PERCENTAGE_CHANGE_1H: String = "percent_change_1h"
        private const val USD: String = "USD"
        private const val PRICE: String = "price"

    }
}
