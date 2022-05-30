package com.mokhtar.currencyconverterapp.di

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.mokhtar.currencyconverterapp.R
import com.mokhtar.currencyconverterapp.util.IMAGE_URL

@BindingAdapter("imageUrl")
fun ImageView.loadUrl(countryName:String)
{
    if(countryName.isNotEmpty())
    {
        val completeUrl= "$IMAGE_URL$countryName.png"
        load(completeUrl)
        {
            placeholder(R.drawable.ic_place_holder)
            crossfade(true)
            crossfade(20)
            transformations(CircleCropTransformation())
        }
    }
}