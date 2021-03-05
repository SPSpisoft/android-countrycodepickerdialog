package com.spisoft.countrycodepickerdialog.fetcher

import org.json.JSONArray

interface CountryCodesJsonFetcher {
    fun getJson(): JSONArray
}