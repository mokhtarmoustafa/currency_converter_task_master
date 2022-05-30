package com.mokhtar.currencyconverterapp.ui.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mokhtar.currencyconverterapp.R
import com.mokhtar.currencyconverterapp.databinding.CurrencyDataItemBinding
import com.mokhtar.currencyconverterapp.databinding.CurrencyValueItemBinding
import com.mokhtar.currencyconverterapp.model.convert.ConvertData
import com.mokhtar.currencyconverterapp.model.convert.ValueData

    class HistoryValuesAdapter() :
    RecyclerView.Adapter<HistoryValuesAdapter.HeadlinesViewHolder>() {


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ValueData>() {

        override fun areItemsTheSame(oldItem: ValueData, newItem: ValueData): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ValueData, newItem: ValueData): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesViewHolder {
        return HeadlinesViewHolder(
            CurrencyValueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override
    fun onBindViewHolder(holder: HeadlinesViewHolder, position: Int) {
        when (holder) {
            is HeadlinesViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }
        override
        fun getItemCount(): Int = differ.currentList.size


        fun submitList(list: List<ValueData>) {
            differ.submitList(list)
        }

    class HeadlinesViewHolder(val binding: CurrencyValueItemBinding) : RecyclerView.ViewHolder(binding.root) {

       fun bind(valueData:ValueData)
       {
           binding.valueData=valueData
       }
    }
}