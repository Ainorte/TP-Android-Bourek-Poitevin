package com.mbds.newsletter.tools

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
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