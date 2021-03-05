package com.spisoft.countrycodepickerdialog.ui.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.dialog_country_code_picker.*
import com.spisoft.countrycodepickerdialog.R
import com.spisoft.countrycodepickerdialog.fetcher.*
import com.spisoft.countrycodepickerdialog.listeners.OnCountryCodeSelectedListener
import com.spisoft.countrycodepickerdialog.models.CountryCode
import com.spisoft.countrycodepickerdialog.ui.adapters.CountryCodePickerDialogAdapter
import com.spisoft.countrycodepickerdialog.utils.CountryCodesUtil

class CountryCodePickerDialogFragment : DialogFragment(), OnCountryCodeSelectedListener {

    companion object {
        private const val EXTRA_DEFAULT_COUNTRY_SELECTED = "EXTRA_DEFAULT_COUNTRY"

        fun getInstance(defaultSelectedCountryCode: String? = null): CountryCodePickerDialogFragment {
            val fragment = CountryCodePickerDialogFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_DEFAULT_COUNTRY_SELECTED, defaultSelectedCountryCode)
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var imageFetcher: ImageFetcher
    lateinit var jsonFetcher: CountryCodesJsonFetcher

    private val adapter by lazy {
        CountryCodePickerDialogAdapter(this, imageFetcher)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (!::jsonFetcher.isInitialized) {
            jsonFetcher = CountryCodesJsonFromDiskFetcher(requireContext())
            imageFetcher = RemoteImageFetcher(requireContext())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_country_code_picker, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupSearch()
        setupDefaultSelectedCountry()
    }

    private fun setupAdapter() {
        val countryCodes = CountryCodesFetcher(jsonFetcher).getCountryCodes()
        viewDialogCountryCodePickerRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewDialogCountryCodePickerRv.adapter = adapter
        adapter.setCountryCodes(countryCodes)
    }

    private fun setupSearch() {
        viewDialogCountryCodePickerSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                text?.let {
                    val newFilteredCountryCodes = adapter.filterCountryCodes(it.toString())
                    if (newFilteredCountryCodes.isEmpty()) {
                        viewDialogCountryCodePickerEmpty.visibility = View.VISIBLE
                        viewDialogCountryCodePickerRv.visibility = View.GONE
                    } else {
                        viewDialogCountryCodePickerEmpty.visibility = View.GONE
                        viewDialogCountryCodePickerRv.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setupDefaultSelectedCountry() {
        val defaultCountryCode = arguments?.getString(EXTRA_DEFAULT_COUNTRY_SELECTED) ?: ""
        if (!defaultCountryCode.isEmpty()) {
            val position = CountryCodesUtil.getPositionForCountry(defaultCountryCode, adapter.countryCodes)
            viewDialogCountryCodePickerRv.scrollToPosition(position)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

//    override fun onCountryCodeSelected(countryCode: CountryCode) {
//        dismiss()
//        if (parentFragment is OnCountryCodeSelectedListener) {
//            (parentFragment as OnCountryCodeSelectedListener).onCountryCodeSelected(countryCode, null)
//        } else {
//            if (activity is OnCountryCodeSelectedListener) {
//                (activity as OnCountryCodeSelectedListener).onCountryCodeSelected(countryCode, null)
//            }
//        }
//    }

    override fun onCountryCodeSelected(countryCode: CountryCode, image: Drawable?) {
        dismiss()
        if (parentFragment is OnCountryCodeSelectedListener) {
            (parentFragment as OnCountryCodeSelectedListener).onCountryCodeSelected(countryCode, image)
        } else {
            if (activity is OnCountryCodeSelectedListener) {
                (activity as OnCountryCodeSelectedListener).onCountryCodeSelected(countryCode, image)
            }
        }
    }
}