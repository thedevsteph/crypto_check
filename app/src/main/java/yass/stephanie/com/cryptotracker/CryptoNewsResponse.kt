package yass.stephanie.com.cryptotracker

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CryptoNewsResponse {

    @SerializedName("articles")
    @Expose
    var articles: ArrayList<Article> = arrayListOf()
}


class Article {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("urlToImage")
    var urlImage: String? = null

    @SerializedName("publishedAt")
    var date: String? = null


}

