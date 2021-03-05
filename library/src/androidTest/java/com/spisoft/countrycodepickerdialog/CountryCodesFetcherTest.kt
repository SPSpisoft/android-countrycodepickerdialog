package com.spisoft.countrycodepickerdialog

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.spisoft.countrycodepickerdialog.fetcher.CountryCodesFetcher
import com.spisoft.countrycodepickerdialog.models.CountryCode
import com.spisoft.countrycodepickerdialog.util.CountryCodesJsonFromMemoryFetcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountryCodesFetcherTest {

    private lateinit var fetcher: CountryCodesFetcher

    @Before
    fun init() {
        fetcher = CountryCodesFetcher(CountryCodesJsonFromMemoryFetcher())
    }

    @Test
    fun testShouldReturnListOfCountryCodes() {
        val expectedCountry1 = CountryCode(name = "Afghanistan",
                dialCode = "+93",
                code = "AF")
        assertEquals(fetcher.getCountryCodes()[0], expectedCountry1)
    }


}