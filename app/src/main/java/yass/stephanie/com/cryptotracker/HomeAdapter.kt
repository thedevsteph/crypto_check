package yass.stephanie.com.cryptotracker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class HomeAdapter(private val data: ArrayList<Currencies>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    class HomeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val currencyLongText: TextView = view.findViewById(R.id.currency_long_text)
        val currencyShortText: TextView = view.findViewById(R.id.currency_short_text)
        val percentagePriceChange: TextView = view.findViewById(R.id.percentage_price_change)
        val currentHoldingValue: TextView = view.findViewById(R.id.current_holding_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_homescreen_screen, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.currencyLongText.text = data[position].longName
        holder.currencyShortText.text = data[position].abbreviatedName
        holder.percentagePriceChange.text = "45.40%"
        holder.currentHoldingValue.text = "£${data[position].currentPrice}"

        //change the background - temporary code
        when {
            position % 2 == 0 -> stringProvider(holder, R.color.abc_btn_colored_borderless_text_material)
            position % 5 == 0 -> stringProvider(holder, R.color.error_color_material_light)
            else -> null
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun stringProvider(holder: HomeViewHolder, colorInt: Int){
        val color = holder.itemView.context.getColor(colorInt)
        holder.percentagePriceChange.setTextColor(color)
    }



}