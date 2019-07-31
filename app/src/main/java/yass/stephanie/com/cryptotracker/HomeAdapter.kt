package yass.stephanie.com.cryptotracker

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class HomeAdapter(private val data: ArrayList<Currencies>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(),
    View.OnClickListener {

    companion object {
        lateinit var context: Context
        private const val CURRENCY_NAME = "CURRENCY_NAME"
    }

    class HomeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val currencyLongText: TextView = view.findViewById(R.id.currency_long_text)
        val currencyShortText: TextView = view.findViewById(R.id.currency_short_text)
        val percentagePriceChange: TextView = view.findViewById(R.id.percentage_price_change)
        val currentHoldingValue: TextView = view.findViewById(R.id.current_holding_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_homescreen_screen, parent, false)
        view.setOnClickListener(this)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        context = holder.view.context
        holder.currencyLongText.text = data[position].longName
        holder.currencyShortText.text = data[position].abbreviatedName
        holder.percentagePriceChange.text = "${data[position].hourlyPercentageChange}%"
        holder.currentHoldingValue.text = "$${data[position].currentPrice}"

        var percentageChange = data[position].hourlyPercentageChange

        percentageChange?.let {
            when {
                percentageChange > 0 -> stringProvider(holder, R.color.brightGreen)
                percentageChange < 0 -> stringProvider(holder, R.color.brightRed)
                else -> null
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun stringProvider(holder: HomeViewHolder, colorInt: Int) {
        val color = holder.itemView.context.getColor(colorInt)
        holder.percentagePriceChange.setTextColor(color)
    }

    override fun onClick(view: View?) {
        var intent = Intent(context, NewsActivity::class.java)
        var stringTextView = view?.findViewById<TextView>(R.id.currency_long_text)
        intent.putExtra(CURRENCY_NAME, stringTextView?.text.toString())
        view?.let { it.context.startActivity(intent) }
    }


}