package com.spisoft.countrycodepickerdialog

import android.content.Context
import com.spisoft.countrycodepickerdialog.fetcher.CountryCodesJsonFetcher
import com.spisoft.countrycodepickerdialog.fetcher.CountryCodesJsonFromDiskFetcher

object CountryCodePickerDataManager {
    fun getJsonFetcher(context: Context): CountryCodesJsonFetcher = CountryCodesJsonFromDiskFetcher(context)
}