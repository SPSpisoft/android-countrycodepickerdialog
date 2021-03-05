package com.spisoft.countrycodepickerdialog.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spisoft.countrycodepickerdialog.R
import com.spisoft.countrycodepickerdialog.fetcher.ImageFetcher
import com.spisoft.countrycodepickerdialog.listeners.OnCountryCodeSelectedListener
import com.spisoft.countrycodepickerdialog.models.CountryCode
import com.spisoft.countrycodepickerdialog.utils.CountryCodesUtil

class CountryCodePickerDialogAdapter(
        private val onCountryCodeSelectedListener: OnCountryCodeSelectedListener?,
        private val imageFetcher: ImageFetcher) : RecyclerView.Adapter<CountryCodePickerDialogAdapter.CountryCodeRowViewHolder>() {

    val countryCodes = mutableListOf<CountryCode>()
    private val filteredCountryCodes = mutableListOf<CountryCode>()

    init {
        filteredCountryCodes.addAll(countryCodes)
    }

    fun setCountryCodes(countryCodes: List<CountryCode>) {
        this.countryCodes.addAll(countryCodes)
        filteredCountryCodes.addAll(countryCodes)
        notifyDataSetChanged()
    }

    fun filterCountryCodes(country: String): List<CountryCode> {
        val newFilteredCountryCodes = CountryCodesUtil.filterCountryCodes(country, countryCodes)
        filteredCountryCodes.clear()
        filteredCountryCodes.addAll(newFilteredCountryCodes)
        notifyDataSetChanged()
        return newFilteredCountryCodes
    }

    inner class CountryCodeRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flag = itemView.findViewById<ImageView>(R.id.viewCountryCodePickerRowFlag)!!
        val country = itemView.findViewById<TextView>(R.id.viewCountryCodePickerRowCountry)!!
        val dialCode = itemView.findViewById<TextView>(R.id.viewCountryCodePickerRowDialCode)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CountryCodeRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dialog_country_code_picker_row, parent, false)
        return CountryCodeRowViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CountryCodeRowViewHolder, position: Int) {
        val countryCode = filteredCountryCodes[position]

        viewHolder.flag.setImageBitmap(null)


        imageFetcher.loadImage(url = "https://www.countryflags.io/${countryCode.code.toLowerCase()}/flat/64.png", callback = {
            viewHolder.flag.setImageBitmap(it)
        })

        viewHolder.country.text = countryCode.name
        viewHolder.dialCode.text = countryCode.dialCode

        viewHolder.itemView.setOnClickListener {
            onCountryCodeSelectedListener?.onCountryCodeSelected(countryCode, viewHolder.flag.drawable)
        }
    }

    override fun getItemCount(): Int {
        return filteredCountryCodes.size
    }

}