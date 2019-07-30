package yass.stephanie.com.cryptotracker

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CryptoDataResponse {

    @SerializedName("data")
    @Expose
    var result: Map<String, JsonObject>? = null

}


