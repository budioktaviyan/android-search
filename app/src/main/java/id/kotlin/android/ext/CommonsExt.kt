package id.kotlin.android.ext

internal inline fun <reified T : Any> clazz() = T::class.java