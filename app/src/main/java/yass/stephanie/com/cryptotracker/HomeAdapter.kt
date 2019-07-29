package yass.stephanie.com.cryptotracker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class HomeAdapter(private val data: ArrayList<Currencies>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    class HomeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val currencyLongText: TextView = view.findViewById(R.id.currency_long_text)
        val currencyShortText: TextView = view.findViewById(R.id.currency_short_text)
        val percentagePriceChange: TextView = view.findViewById(R.id.percentage_price_change)
        var currencyIcon: ImageView = view.findViewById(R.id.currency_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_homescreen_screen, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.currencyLongText.text = data[position].longName
        holder.currencyShortText.text = data[position].abbreviatedName
        holder.percentagePriceChange.text = "45.40%"
        holder.currencyIcon.background = holder.view.context.getDrawable(R.drawable.abc_ab_share_pack_mtrl_alpha)
    }

    override fun getItemCount(): Int {
        return data.size
    }


}