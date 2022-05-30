package com.mokhtar.currencyconverterapp.ui.history.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.mokhtar.currencyconverterapp.R
import com.mokhtar.currencyconverterapp.databinding.CurrencyDataItemBinding
import com.mokhtar.currencyconverterapp.model.convert.ConvertData
import okhttp3.internal.notifyAll

class HistoryDataCurrencyAdapter() :
    RecyclerView.Adapter<HistoryDataCurrencyAdapter.CurrencyViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ConvertData>() {

        override fun areItemsTheSame(oldItem: ConvertData, newItem: ConvertData): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ConvertData, newItem: ConvertData): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {


        return CurrencyViewHolder(
            CurrencyDataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override
    fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        when (holder) {
            is CurrencyViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override
    fun getItemCount(): Int = differ.currentList.size


    fun submitList(list: List<ConvertData>) {
        differ.submitList(list)
    }

    class CurrencyViewHolder(private val binding: CurrencyDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(convertData: ConvertData) {
            binding.convertData = convertData

            val headlineAdapter = HistoryValuesAdapter()
            headlineAdapter.submitList(convertData?.valueData)
            binding.rvHeadlines.adapter = headlineAdapter

            binding.root.setOnClickListener {
                convertData?.isExpanded = !convertData?.isExpanded!!
                if (convertData?.isExpanded!!) {
                    binding.rvHeadlines.visibility = View.VISIBLE
                    binding.ivArrow.setImageResource(R.drawable.ic_arrow_up)
                } else {
                    binding.rvHeadlines.visibility = View.GONE
                    binding.ivArrow.setImageResource(R.drawable.ic_arrow_down)
                }

            }

        }
    }

    fun onItemClicked(position: Int, convertData: ConvertData) {
        convertData?.isExpanded = !convertData?.isExpanded!!
        notifyItemChanged(position)
    }
}