package com.mbds.newsletter.tools

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.model.Category

fun Category.getName(context: Context) = context.getString(nameId)

fun ImageView.setImageFromUrl(imageUrl: String, root: View) =
    Glide.with(root)
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.drawable.placeholder)
        .into(this)