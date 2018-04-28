package id.kotlin.android.ext

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat

fun Context.getDrawableResource(@DrawableRes resource: Int) = ContextCompat.getDrawable(this, resource) ?: throw Exception("Unknown drawable resource!")