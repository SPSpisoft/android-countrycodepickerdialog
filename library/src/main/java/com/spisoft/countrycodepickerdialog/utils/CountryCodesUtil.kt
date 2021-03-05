package com.spisoft.countrycodepickerdialog.utils

import com.spisoft.countrycodepickerdialog.models.CountryCode

object CountryCodesUtil {
    fun filterCountryCodes(country: String, countryCodes: List<CountryCode>): List<CountryCode> {
        return countryCodes.filter { it.name.toLowerCase().startsWith(country.toLowerCase()) || it.dialCode.contains(country) }
    }
    fun getPositionForCountry(countryCode: String, countryCodes: List<CountryCode>): Int {
        val countryCode= countryCodes.firstOrNull { it.code.toLowerCase() == countryCode.toLowerCase() }
        return countryCodes.indexOf(countryCode)
    }
}