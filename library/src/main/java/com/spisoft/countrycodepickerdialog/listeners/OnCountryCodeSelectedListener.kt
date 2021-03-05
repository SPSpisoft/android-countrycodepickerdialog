package com.spisoft.countrycodepickerdialog.listeners

import android.graphics.drawable.Drawable
import com.spisoft.countrycodepickerdialog.models.CountryCode

interface OnCountryCodeSelectedListener {
    fun onCountryCodeSelected(countryCode: CountryCode, image: Drawable?)
}