package com.spisoft.countrycodepickerdialog.util

import android.graphics.Bitmap
import com.spisoft.countrycodepickerdialog.fetcher.ImageFetcher

class DummyImageFetcher : ImageFetcher {
    override fun loadImage(url: String, callback: (bitmap: Bitmap) -> Unit) {
        val bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888 )
        callback.invoke(bitmap)
    }
}