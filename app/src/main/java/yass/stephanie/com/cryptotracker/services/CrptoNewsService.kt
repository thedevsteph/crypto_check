package yass.stephanie.com.cryptotracker.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import yass.stephanie.com.cryptotracker.CryptoNewsResponse

private const val API_KEY = "30ab4a879b1c4a9498253ae2d7a1248f"
private const val NEWS_PATH = "/v2/everything"

interface CryptoNewsService {

    @Headers("X-Api-Key: $API_KEY")
    @GET(NEWS_PATH)
    fun getCryptoNewsInformation(@Query("q") queryString: String, @Query("pageSize") pageSize: Int): Call<CryptoNewsResponse>
}