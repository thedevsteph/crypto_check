package yass.stephanie.com.cryptotracker.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import yass.stephanie.com.cryptotracker.CryptoDataResponse

private const val API_KEY = "eeac20c7-e2de-47ee-8437-9bd757406091"
private const val LATEST_PRICES_PATH = "/v1/cryptocurrency/quotes/latest"

interface CryptoDataService{

    @Headers("X-CMC_PRO_API_KEY: $API_KEY")
    @GET(LATEST_PRICES_PATH)
    fun getCryptoInformation(@Query("symbol") symbol: String) : Call<CryptoDataResponse>


}