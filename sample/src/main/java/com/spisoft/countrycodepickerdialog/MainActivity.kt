package com.spisoft.countrycodepickerdialog

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spisoft.countrycodepickerdialog.listeners.OnCountryCodeSelectedListener
import com.spisoft.countrycodepickerdialog.models.CountryCode
import com.spisoft.countrycodepickerdialog.ui.fragments.CountryCodePickerDialogFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnCountryCodeSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewButton.setOnClickListener {
            CountryCodePickerDialogFragment.Companion.getInstance("ir", "جستجو").show(supportFragmentManager, null)
//            CountryCodePickerDialogFragment.getInstance().show(supportFragmentManager, null)
        }
    }

    override fun onCountryCodeSelected(countryCode: CountryCode, img: Drawable?) {
        viewCountryCode.text = countryCode.toString()
        viewImage.setImageDrawable(img)

//        imageFetcher.loadImage(url = "https://www.countryflags.io/${countryCode.code.toLowerCase()}/flat/64.png", callback = {
//            viewImage.setImageBitmap(it)
//        })
//        try {
//            val url = URL("https://www.countryflags.io/${countryCode.code.toLowerCase()}/flat/64.png")
//            val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//            viewImage.setImageBitmap(image);
//        } catch (e: IOException) {
//            System.out.println(e)
//        }
    }
    interface ImageFetchery {
        fun loadImage(url: String, callback: (bitmap: Bitmap) -> Unit)
    }
}
