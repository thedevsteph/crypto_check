package yass.stephanie.com.cryptotracker

data class Currencies(
    var abbreviatedName: String? = null,
    var currentPrice: String? = "0",
    var longName: String? = null,
    var hourlyPercentageChange: Double? = 0.0,
    var quantity: Double? = 564.0
)