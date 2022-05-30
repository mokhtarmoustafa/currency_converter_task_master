package com.mokhtar.currencyconverterapp.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.get
import com.mokhtar.currencyconverterapp.R
import com.mokhtar.currencyconverterapp.databinding.HistoryFragmentBinding
import com.mokhtar.currencyconverterapp.ui.base.BaseFragment
import com.mokhtar.currencyconverterapp.ui.history.adapter.HistoryDataCurrencyAdapter
import com.mokhtar.currencyconverterapp.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<HistoryViewModel, HistoryFragmentBinding>() {


    //region variables
    override val mViewModel: HistoryViewModel by viewModels()
    private val today = getDate()
    private val previousDate = getDate(7)
    private lateinit var adapter: HistoryDataCurrencyAdapter
    private var isConnected = false

    //endregion
    companion object {
        fun newInstance() = HistoryFragment()
        private const val TAG = "HistoryFragment"
    }


    override fun getViewBinding(view: View): HistoryFragmentBinding =
        HistoryFragmentBinding.inflate(layoutInflater)

    //region events


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleNetworkConnection()

        getConvertData()

        observeConvertData()
    }


    //endregion

    //region helper functions
    private fun observeConvertData() {
        mViewModel.convertLiveData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> {}
                is State.Success -> {
                    if (!state.data.convertData.isNullOrEmpty()) {

                        adapter = HistoryDataCurrencyAdapter()
                        adapter.submitList(state.data.convertData)

                        binding.rvHistory.adapter = adapter
                    }
                }
                is State.Error -> {
                    showToast(state.message)
                }

            }
        })
    }

    private fun handleNetworkConnection() {
        if (checkForInternet(requireContext()))
        {
            isConnected=true
           getConvertData()
        }
        else
            showToast(getString(R.string.no_connection))
    }

    private fun getConvertData() {
            val query =
                findNavController().graph[R.id.historyFragment].arguments["query"]?.defaultValue
            if (query!=null) {
                mViewModel.getConvertData(query.toString(), "compact", previousDate, today)
            }
            else
                showToast("Select Two currencies first")


    }
    //endregion

}