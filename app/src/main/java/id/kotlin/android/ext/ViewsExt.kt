package id.kotlin.android.ext

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup?.inflate(context: Context,
                       layoutRes: Int,
                       attachToRoot: Boolean): View = LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)