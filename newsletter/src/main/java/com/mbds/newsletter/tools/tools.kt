package com.mbds.newsletter.tools

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.mbds.newsletter.model.Category

fun Category.getName(context: Context) = context.getString(nameId)

fun ImageView.setImageFromUrl(imageUrl: String, @DrawableRes placeholder: Int, root: View) =
    Glide.with(root)
        .load(imageUrl)
        .centerCrop()
        .placeholder(placeholder)
        .into(this)

fun Context.isOnline(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}