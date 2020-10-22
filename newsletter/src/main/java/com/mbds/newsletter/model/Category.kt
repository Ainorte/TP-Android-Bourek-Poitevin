package com.mbds.newsletter.model

import androidx.annotation.StringRes
import java.io.Serializable

data class Category (@StringRes val nameId: Int, val key: String, val image: String) : Serializable{}