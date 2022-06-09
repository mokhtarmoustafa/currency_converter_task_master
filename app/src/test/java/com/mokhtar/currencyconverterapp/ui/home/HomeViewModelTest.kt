package com.mokhtar.currencyconverterapp.ui.home


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mokhtar.currencyconverterapp.data.remote.repository.MockCurrencyRepo
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.util.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.awaitility.Awaitility
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


class HomeViewModelTest {

    private lateinit var vm: HomeViewModel
    private lateinit var repo: MockCurrencyRepo

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val mainThreadSurrogate = newSingleThreadContext("Main Thread")
    @Before
    fun initMocksAndMainThread() {
        Dispatchers.setMain(mainThreadSurrogate)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Before
    fun setUp() {
        repo = MockCurrencyRepo()
        vm = HomeViewModel(repo)
    }


    @Test
    fun getCurrencyLiveData() {
        val latch = CountDownLatch(1)
        var currencyResponse: CurrencyResponse? = null
        val observer = object : Observer<State<CurrencyResponse>> {
            override fun onChanged(receivedPlants: State<CurrencyResponse>?) {
                val s = vm.currencyLiveData.value
                when (s) {
                    is State.Success -> {
                        currencyResponse = s.data
                    }
                    is State.Error -> {
                        fail("Couldn't get currency response")
                    }
                }
                latch.countDown()
                vm.currencyLiveData.removeObserver(this)
            }
        }
        vm.currencyLiveData.observeForever(observer)
        vm.getCurrencies()
        latch.await(10, TimeUnit.SECONDS)
        val r = repo.currencyResponse
        assertNotNull("Null currency response", currencyResponse)
        assertEquals("Currency response from VM not equal to repo", currencyResponse, r)
    }





}