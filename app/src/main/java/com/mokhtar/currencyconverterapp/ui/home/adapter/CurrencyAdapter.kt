package com.mokhtar.currencyconverterapp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.mokhtar.currencyconverterapp.databinding.CurrencyListItemBinding
import com.mokhtar.currencyconverterapp.model.currency.Currency
import java.util.*
import kotlin.collections.ArrayList

class CurrencyAdapter(
    private val mContext: Context,
    private val mLayoutResourceId: Int,
    cities: List<Currency>
) :
    ArrayAdapter<Currency>(mContext, mLayoutResourceId, cities) {
    private val city: MutableList<Currency> = ArrayList(cities)
    private var allCities: List<Currency> = ArrayList(cities)

    override fun getCount(): Int {
        return city.size
    }
    override fun getItem(position: Int): Currency {
        return city[position]
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        var binding:CurrencyListItemBinding

        if (convertView == null) {
            val inflater = LayoutInflater.from(parent.context)
            binding = CurrencyListItemBinding.inflate(inflater, parent, false)
            convertView=binding.root
        }
        else
            binding= convertView.tag as CurrencyListItemBinding

        try {
            binding.currency=getItem(position)

            convertView.tag = binding
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertView!!
    }

    class MyViewHolder
    constructor(
        private val  binding: CurrencyListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Currency){
          binding.currency=item

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Currency)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun convertResultToString(resultValue: Any) :String {
                return (resultValue as Currency).currencyName
            }
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint != null) {
                    val citySuggestion: MutableList<Currency> = ArrayList()
                    for (city in allCities) {
                        if (city.currencyName.lowercase(Locale.getDefault())
                                .startsWith(constraint.toString().lowercase(Locale.getDefault()))
                        ) {
                            citySuggestion.add(city)
                        }
                    }
                    filterResults.values = citySuggestion
                    filterResults.count = citySuggestion.size
                }
                return filterResults
            }
            override fun publishResults(
                constraint: CharSequence?,
                results: FilterResults
            ) {
                city.clear()
                if (results.count > 0) {
                    for (result in results.values as List<*>) {
                        if (result is Currency) {
                            city.add(result)
                        }
                    }
                    notifyDataSetChanged()
                } else if (constraint == null) {
                    city.addAll(allCities)
                    notifyDataSetInvalidated()
                }
            }
        }
    }
}
