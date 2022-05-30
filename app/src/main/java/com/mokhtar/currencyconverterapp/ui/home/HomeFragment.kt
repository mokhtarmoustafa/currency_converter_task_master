package com.mokhtar.currencyconverterapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgument
import androidx.navigation.fragment.findNavController
import com.mokhtar.currencyconverterapp.R
import com.mokhtar.currencyconverterapp.databinding.HomeFragmentBinding
import com.mokhtar.currencyconverterapp.model.convert.ConvertData
import com.mokhtar.currencyconverterapp.model.currency.Currency
import com.mokhtar.currencyconverterapp.ui.base.BaseFragment
import com.mokhtar.currencyconverterapp.ui.home.adapter.CurrencyAdapter
import com.mokhtar.currencyconverterapp.util.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding>() {

    //region variables

    override val mViewModel: HomeViewModel by viewModels()
    private lateinit var adapterFrom: CurrencyAdapter
    private lateinit var adapterTo: CurrencyAdapter
    private var currencyList = mutableListOf<Currency>()
    private lateinit var fromCurrency: Currency
    private lateinit var toCurrency: Currency
    private val today = getDate()
    var query = ""
    private var isConnected=false

    //endregion
    companion object {
        fun newInstance() = HomeFragment()
        private const val TAG = "HomeFragment"
    }


    //region events
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleNetworkConnection()

        observeCurrencyData()

        observeConvertData()



    }


    override fun getViewBinding(view: View) = HomeFragmentBinding.inflate(layoutInflater)

    //endregion


    //region helper functions

    private fun getConvertData(
        query: String,
        compact: String,
        date: String,
        endDate: String?
    ) {
        if (!query.isNullOrEmpty())
            mViewModel.getConvertData(query, compact, date, endDate)


    }

    private fun displayConvertData(fromValue: ConvertData) {

        binding.etFrom.doAfterTextChanged { s ->
            if (validateSelectedCurrency()) {
                if (!s.toString().trim().isNullOrEmpty()) {
                    val toCurrency = s.toString().toInt() * fromValue.valueData[0].totalValue
                    binding.etTo.setText(toCurrency.toString())
                } else {
                    binding.etFrom.text.clear()
                    binding.etTo.text.clear()
                }

            } else
                Toast.makeText(requireContext(), "No data available", Toast.LENGTH_SHORT).show()
        }

    }

    private fun validateSelectedCurrency(): Boolean {
        if (fromCurrency == null) {
            Toast.makeText(requireContext(), "Please select from currency", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (toCurrency == null) {
            Toast.makeText(requireContext(), "Please select to currency", Toast.LENGTH_SHORT).show()
            return false
        }


        return true

    }

    private fun observeConvertData() {
        mViewModel.convertLiveData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> {}
                is State.Success -> {
                    if (!state.data.convertData.isNullOrEmpty()) {
                        val convertData = state.data.convertData.first()
                        displayConvertData(convertData)
                    }
                }
                is State.Error -> {
                    showToast(state.message)
                }

            }
        })
    }

    private fun observeCurrencyData() {
        mViewModel.currencyLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {}
                is State.Success -> {
                    if (state.data!=null&&!state.data.results.isNullOrEmpty()) {
                        currencyList = state.data.results

                        adapterFrom = CurrencyAdapter(
                            requireContext(),
                            R.layout.currency_list_item,
                            currencyList
                        )
                        adapterTo = adapterFrom

                        fromCurrency = currencyList.first()
                        toCurrency = fromCurrency


                        binding.acFrom.setAdapter(adapterFrom)
                        binding.acTo.setAdapter(adapterTo)

                        binding.acFrom.setText(fromCurrency.currencyName, false)
                        binding.acTo.setText(toCurrency.currencyName, false)

                        binding.acFrom.setOnItemClickListener { parent, _, i, _ ->

                            if(checkForInternet(requireContext()))
                            {
                                fromCurrency = parent.getItemAtPosition(i) as Currency
                                query =
                                    "${fromCurrency.id}_${toCurrency.id},${toCurrency.id}_${fromCurrency.id}"

                                getConvertData(query, "compact", today, today)

                                findNavController().graph.findNode(R.id.historyFragment)?.addArgument(
                                    "query",
                                    NavArgument.Builder().setDefaultValue(query).build()
                                )
                            }
                            else
                                showToast(getString(R.string.no_connection))

                        }

                        binding.acTo.setOnItemClickListener { parent, _, i, _ ->
                            if(checkForInternet(requireContext()))
                          {
                              toCurrency = parent.getItemAtPosition(i) as Currency

                              query =
                                  "${fromCurrency.id}_${toCurrency.id},${toCurrency.id}_${fromCurrency.id}"
                              getConvertData(query, "compact", today, null)

                              findNavController().graph.findNode(R.id.historyFragment)?.addArgument(
                                  "query",
                                  NavArgument.Builder().setDefaultValue(query).build()
                              )
                          }
                            else
                            showToast(getString(R.string.no_connection))

                        }

                    }


                }
                is State.Error -> {
                    showToast(state.message)

                }

            }

        }
    }


    private fun handleNetworkConnection() {
        if (checkForInternet(requireContext()))
        {
            isConnected=true
            mViewModel.getCurrencies()
        }
        else
            showToast(getString(R.string.no_connection))
    }
    //endregion
}