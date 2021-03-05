package com.spisoft.countrycodepickerdialog.fetcher

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide


interface ImageFetcher {
    fun loadImage(url: String, callback: (bitmap: Bitmap) -> Unit)

    fun loadImage2(vararg url: String): Bitmap? {
        val imageURL = url[0]
        var image: Bitmap? = null
        try {
            val `in` = java.net.URL(imageURL).openStream()
            image = BitmapFactory.decodeStream(`in`)
        } catch (e: Exception) {
            Log.e("Error Message", e.message.toString())
            e.printStackTrace()
        }
        return image
    }

    fun loadImage3(context: Context, vararg url: String): Bitmap? {
        var image: Bitmap? = null
        try {
            image = Glide
                .with(context)
                .asBitmap()
                .load(url)
                .submit()
                .get()
        } catch (e: Exception) {
            Log.e("Error Message", e.message.toString())
            e.printStackTrace()
        }
        return image
    }

    class loadImage4(context: Context, url: String, flag: ImageView) : AsyncTask<Void, Void, Bitmap>() {
        val context = context
        val url = url
        val flag = flag
        override fun doInBackground(vararg params: Void?): Bitmap? {
            var image: Bitmap? = null
            try {
                image = Glide
                    .with(context)
                    .asBitmap()
                    .load(url)
                    .submit()
                    .get()
            } catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            flag.setImageBitmap(result)
        }
    }

//    @SuppressLint("StaticFieldLeak")
//    @Suppress("DEPRECATION")
//    private class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
//        init {
//        }
//        override fun doInBackground(vararg urls: String): Bitmap? {
//            val imageURL = urls[0]
//            var image: Bitmap? = null
//            try {
//                val `in` = java.net.URL(imageURL).openStream()
//                image = BitmapFactory.decodeStream(`in`)
//            }
//            catch (e: Exception) {
//                Log.e("Error Message", e.message.toString())
//                e.printStackTrace()
//            }
//            return image
//        }
//        override fun onPostExecute(result: Bitmap?) {
//            imageView.setImageBitmap(result)
//        }
//    }
}